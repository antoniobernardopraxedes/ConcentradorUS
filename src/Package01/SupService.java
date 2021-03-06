package Package01;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class SupService {

    private static boolean verbose;
    private static String endIpServidor;
    private static String endIpConcArd;
    private static String endIPHost;

    public static boolean isVerbose() {
        return verbose;
    }

    public static String getEndIpServidor() {
        return endIpServidor;
    }

    public static String getEndIpConcArd() {
        return endIpConcArd;
    }

    public static String getEndIPHost() {
        return endIPHost;
    }

    //******************************************************************************************************************
    // Nome do Método: LeArquivoConfiguracao()                                                                         *
    //	                                                                                                               *
    // Funcao: lê o arquivo de configuração                                                                            *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: boolean: true: o arquivo de configuração foi lido corretamente / false: falha em ler o arquivo           *
    //******************************************************************************************************************
    //
    public static boolean LeArquivoConfiguracao() {
        boolean leArqOK = true;
        String arquivoTxt = null;
        String caminho = "recursos/";
        String nomeArquivo = "concentradorus.cnf";

        Arquivo arquivo = new Arquivo();

        try {
            if (arquivo.Existe(caminho, nomeArquivo)) {
                arquivoTxt = arquivo.LeTexto(caminho, nomeArquivo);

                String Verbose = LeParametroArqConf(arquivoTxt, "Verbose:");
                String EndIpServidor = LeParametroArqConf(arquivoTxt, "EndIpServidor:");
                String EndIpConcArduino = LeParametroArqConf(arquivoTxt, "EndIpConcArduino:");
                String EndIPHost = LeParametroArqConf(arquivoTxt, "EndIPHost:");

                if (Verbose.equals("true")) {
                    verbose = true;
                }
                else {
                    verbose = false;
                }
                endIpServidor = EndIpServidor;
                endIpConcArd = EndIpConcArduino;
                endIPHost = EndIPHost;
            }
            else {
                leArqOK = false;
            }
        } catch (Exception e) {
            leArqOK = false;
        }
        return leArqOK;
    }

    //******************************************************************************************************************
    // Nome do Método: LeParametroArqConf                                                                              *
    //	                                                                                                               *
    // Funcao: lê um parâmetro após um token de idenfificação em um arquivo texto (configuração)                       *
    //                                                                                                                 *
    // Entrada: string com o arquivo texto e string com o token                                                        *
    //                                                                                                                 *
    // Saida: string com o parâmetro lido após o token de identificação.                                               *
    //******************************************************************************************************************
    //
    private static String LeParametroArqConf (String arquivo, String token){
        int Indice = arquivo.lastIndexOf(token);
        int indiceF = arquivo.length() - 1;
        String parametro = null;
        if (Indice >= 0) {
            Indice = Indice + token.length() + 1;
            String Substring = arquivo.substring(Indice, indiceF);
            StringTokenizer parseToken = new StringTokenizer(Substring);
            parametro = parseToken.nextToken();
        }
        return parametro;
    }

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome da Rotina: BinSrv                                                                                          *
    //                                                                                                                 *
    // Funcao: envia uma mensagem de bytes recebida de um Buffer para um servidor na nuvem e recebe a resposta         *
    //                                                                                                                 *
    // Entrada: Endereço IP do servidor, porta de conexão, Mensagem a ser enviada (POST) e o recurso (query)           *
    //                                                                                                                 *
    // Saida: String com a mensagem de resposta do servidor (se a mensagem é vazia, houve falha de comunicação         *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    static String BinSrv(String EndIPSrv, int PortaSrv, String IPHost, byte[] ByteBuf, String Recurso, boolean Verbose) {
        String MsgRec = "";
        String Metodo = "POST";
        PrintWriter EnvChar = null; BufferedOutputStream EnvByte = null;
        InputStreamReader RecByte = null; BufferedReader RecChar = null;

        try {
            Socket socket = new Socket(EndIPSrv, PortaSrv);
            socket.setSoTimeout(3000);

            if (socket.isConnected()) {
                String MsgTerm = "Atualizador conectou ao Socket: " + socket.toString();
                SupService.Terminal(MsgTerm, false, Verbose);
            }
            EnvByte = new BufferedOutputStream(socket.getOutputStream());
            EnvChar = new PrintWriter(EnvByte, true);
            RecChar = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int TamMsgBin = ByteBuf.length;

            // Envia o Cabeçalho HTTP para o Servidor
            EnvChar.println("POST /" + Recurso + " HTTP/1.1\r");
            EnvChar.println("Content-Type: application/octet-stream;charset=utf-8\r");
            EnvChar.println("User-Agent: (Linux x86_64) PraxClient/1.0\r");
            EnvChar.println("Accept: */*\r");
            EnvChar.println("Host: " + IPHost + ":8080\r");
            EnvChar.println("Accept-Encoding: gzip, deflate, br\r");
            EnvChar.println("Connection: keep-alive\r");
            EnvChar.println("Content-length: " + TamMsgBin + "\r");
            EnvChar.println();
            EnvChar.flush();

            // Envia a mensagem binária para o servidor
            EnvByte.write(ByteBuf, 0, TamMsgBin);
            EnvByte.flush();

            String MsgTrm = "Enviada Requisicao: " + Metodo + " /" + Recurso + " HTTP/1.1 com " + TamMsgBin + " Bytes";
            SupService.Terminal(MsgTrm, false, Verbose);

            try {
                String linha;
                while ((linha = RecChar.readLine()) != null) {
                    MsgRec = MsgRec + linha + "\n";
                }
            }
            catch(java.net.SocketTimeoutException tmo) {
                SupService.Terminal("Timeout na resposta do Servidor", false, Verbose);
            }
            socket.close();
        }
        catch (IOException err) {
            SupService.Terminal(" - Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return(MsgRec);
    }

    //************************************************************************************s*****************************
    //                                                                                                                 *
    // Nome do Método: Json                                                                                            *
    //	                                                                                                               *
    // Funcao: envia para o servidor conectado uma mensagem em formato Json lida de uma String e espera a resposta     *
    //                                                                                                                 *
    // Entrada: Endereço IP e POrta para conexão no Servidor, String com a Mensagem a ser Enviada; String com o        *
    //          Recurso e boolean Verbose (habilita envio de mensagens para o terminal)                                *
    //                                                                                                                 *
    // Saida: string com a mensagem de resposta do servidor                                                            *
    //	                                                                                                               *
    //******************************************************************************************************************
    //
    public static String JsonSrv(String IPSrv, int Porta, String IPHost, String Msg, String Recurso, boolean Verbose) throws IOException {

        //PrintWriter out = null;
        PrintWriter EnvChar = null;
        BufferedOutputStream EnvByte = null;
        InputStreamReader RecByte = null;
        BufferedReader RecChar = null;

        boolean MsgEnvOK;
        String MsgSrv = "";

        Socket socket = null;
        try {
            socket = new Socket(IPSrv, Porta);
            socket.setSoTimeout(3000);
            if (socket.isConnected()) {
                String MsgTerm = "O Cliente Atualizador conectou ao " + socket.toString();
                SupService.Terminal(MsgTerm, false, Verbose);
            }
            //out = new PrintWriter(socket.getOutputStream());
            EnvByte = new BufferedOutputStream(socket.getOutputStream());
            EnvChar = new PrintWriter(EnvByte, true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            RecChar = new BufferedReader(isr);

            //RecChar = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int TamMsg = Msg.length();

            // Envia o Cabeçalho HTTP para o Servidor
            EnvChar.println("POST /" + Recurso + " HTTP/1.1\r");
            EnvChar.println("Content-Type: application/json;charset=utf-8\r");
            EnvChar.println("User-Agent: (Linux x86_64) PraxClient/1.0\r");
            EnvChar.println("Accept: */*\r");
            EnvChar.println("Host: " + IPHost + ":8080\r");
            EnvChar.println("Accept-Encoding: gzip, deflate, br\r");
            EnvChar.println("Connection: keep-alive\r");
            EnvChar.println("Content-length: " + TamMsg + "\r");
            EnvChar.println();

            // Envia a Mensagem em Formato Json (UTF-8) para o Servidor
            EnvChar.print(Msg);
            EnvChar.flush();
            MsgEnvOK = true;
            SupService.Terminal("Enviada Mensagem Json com " + TamMsg + " Caracteres", false, Verbose);

            try {

                char[] MsgRecSrvChar = new char[512];
                RecChar.read(MsgRecSrvChar);
                char Ch = 0;
                int i = 0;
                do {
                    Ch = MsgRecSrvChar[i];
                    if (Ch != 0) {
                        MsgSrv = MsgSrv + Ch;
                    }
                    i = i + 1;
                } while ((Ch != 0) && (i < 512));

                SupService.Terminal("Recebida Mensagem do Servidor", false, Verbose);

                //System.out.println("");
                //System.out.println(MsgSrv);

                socket.close();
            }
            catch(java.net.SocketTimeoutException tmo) {
                SupService.Terminal("Timeout na resposta do Servidor", false, Verbose);
                socket.close();
            }
        } catch (IOException ioe) {
            MsgEnvOK = false;
            SupService.Terminal("Erro ao enviar a mensagem HTTP para o Servidor: " + ioe, false, Verbose);
            socket.close();
        }
        return MsgSrv;

    } // Fim do Método

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: CoAPUDP                                                                                         *
    //                                                                                                                 *
    // Funcao: envia uma mensagem de requisição e recebe a mensagem de resposta do Controlador Arduino Mega            *
    //         em Protocolo CoAP                                                                                       *
    //                                                                                                                 *
    // Byte |           0         |      1       |      2      |        3        |        4        |        5        | *
    // Bit  | 7 6 | 5 4 | 3 2 1 0 |  7654  3210  |  7654 3210  | 7 6 5 4 3 2 1 0 | 7 6 5 4 3 2 1 0 | 7 6 5 4 3 2 1 0 | *
    //      | Ver |Tipo |  Token  | Código (c.m) | Message ID  |      Option     |   Payload ID    |                   *
    //                                                                                                                 *
    // Ver (Versão) = 01 (O número da versão do protocolo CoAP é fixo)  / TKL (Token) = 0000 (não é usado)             *
    // Tipo (de Mensagem): 00 Confirmável (CON) / 01 Não-Confirmável (NON) / 10 Reconhecimento (ACK) / 11 Reset (RST)  *
    //                                                                                                                 *
    // Códigos de Solicitação: 0000 0000 EMPTY / 0000 0001 GET   / 0000 0010 POST / 0000 0011 PUT / 0000 0100 DELETE   *
    //                                                                                                                 *
    // Códigos de Resposta (Sucesso): 0100 0001 Created / 0100 0010 Deleted / 0100 0011 Valid / 0100 0100 Changed      *
    //                                0100 0101 Content                                                                *
    //                                                                                                                 *
    // Códigos de Erro Cliente: 1000 0000 Bad Request / 1000 0001 Unauthorized / 1000 0010 Bad Option                  *
    //                          1000 0011 Forbidden / 1000 0100 Not Found / 1000 0101 Method Not Allowed               *
    //                          1000 0110 Not Acceptable / 1000 1100 Request Entity Incomplete                         *
    //                                                                                                                 *
    // Códigos de Erro Servidor: 1010 0000 Internal Server Error / 1010 0001 Not Implemented / 1010 0010 Bad Gateway   *
    //                           1010 0011 Service Unavailable / 1010 0100 Gateway Timeout                             *
    //                           1010 0101 Proxying Not Supported                                                      *
    //                                                                                                                 *
    // Message ID (Identificação da mensagem): inteiro de 16 bits sem sinal Mensagem Enviada e Mensagem de Resposta    *
    //                                         com mesmo ID                                                            *
    //                                                                                                                 *
    // Option (Opções) = 0000 0000 (não é usado) / Identificador de Início do Payload: 1111 1111                       *
    //******************************************************************************************************************
    //
    static byte[] CoAPUDP(String EndIP, String URI, int ContMsgCoAP, int Comando, boolean Verbose) {
        int TamMsgRspCoAP = 320;
        int TamMsgSrv = 320;
        int PortaUDP = 5683;
        byte [] MsgRecCoAP = new byte[TamMsgRspCoAP];
        byte [] MsgEnvSrv = new byte[TamMsgSrv];

        try {
            byte[] MsgReqCoAP = new byte[32];

            int TamURI = URI.length();
            byte DH[] = new byte[6];
            DH = SupService.LeDataHora();

            MsgReqCoAP[0] = 0x40;                       // Versão = 01 / Tipo = 00 / Token = 0000
            MsgReqCoAP[1] = 0x01;                       // Código de Solicitação: 0.01 GET
            ContMsgCoAP = ContMsgCoAP + 1;              // Incrementa o Identificador de mensagens
            MsgReqCoAP[2] = SupService.ByteHigh(ContMsgCoAP); // Byte Mais Significativo do Identificador da Mensagem
            MsgReqCoAP[3] = SupService.ByteLow(ContMsgCoAP);  // Byte Menos Significativo do Identificador da Mensagem
            MsgReqCoAP[4] = (byte) (0xB0 + TamURI);     // Delta: 11 - Primeira Opcao 11: Uri-path e Núm. Bytes da URI
            int j = 5;
            for (int i = 0; i < TamURI; i++) {          // Carrega os codigos ASCII da URI
                char Char = URI.charAt(i);
                int ASCII = (int) Char;
                MsgReqCoAP[i + 5] = (byte) ASCII;
                j = j + 1;
            }
            MsgReqCoAP[j] = (byte) 0x11;    // Delta: 1 - Segunda Opcao (11 + 1 = 12): Content-format e Núm. Bytes (1)
            j = j + 1;
            MsgReqCoAP[j] = 42;             // Codigo da Opcao Content-format: application/octet-stream
            j = j + 1;
            MsgReqCoAP[j] = -1;             // Identificador de Inicio do Payload (255)
            j = j + 1;
            MsgReqCoAP[j] = (byte)Comando;  // Carrega o Código do Comando no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[0];          // Carrega a Hora do Computador no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[1];          // Carrega a Minuto do Computador no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[2];          // Carrega a Segundo do Computador no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[3];          // Carrega a Dia do Computador no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[4];          // Carrega a Mes do Computador no Payload
            j = j + 1;
            MsgReqCoAP[j] = DH[5];          // Carrega a Ano do Computador no Payload
            j = j + 1;
            int TamCab = j;                 // Carrega o número de bytes do cabeçalho

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(EndIP);
            clientSocket.setSoTimeout(1000);
            DatagramPacket sendPacket = new DatagramPacket(MsgReqCoAP, TamCab, IPAddress, PortaUDP);
            DatagramPacket receivePacket = new DatagramPacket(MsgRecCoAP, TamMsgRspCoAP);

            clientSocket.send(sendPacket);
            SupService.Terminal("Enviada Requisicao CoAP para o Concentrador", false, Verbose);

            // Espera a Mensagem CoAP de Resposta. Se a mensagem de resposta  for recebida, carrega nas variáveis
            try {
                clientSocket.receive(receivePacket);
                MsgRecCoAP[30] = 1;

                SupService.Terminal("Recebida Mensagem CoAP do Concentrador", false, Verbose);
            } catch (java.net.SocketTimeoutException e) {
                MsgRecCoAP[0] = 0x40;
                MsgRecCoAP[1] = 1;
                MsgRecCoAP[30] = 0;
                SupService.Terminal(" - Erro: o Concentrador nao Respondeu " + MsgRecCoAP[14], false, Verbose);
            }
            clientSocket.close();
        } catch (IOException err) {
            SupService.Terminal("Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return (MsgRecCoAP);
    }

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: BinUDP1                                                                                         *
    //                                                                                                                 *
    // Funcao: envia uma mensagem de requisição e recebe a mensagem de resposta do Controlador de Água Quente          *
    //         em formato binário                                                                                      *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    static int BinUDP1(String EndIP, int Porta, boolean Verbose) {
        int TamMsgRsp = 84;

        try {
            byte[] MsgReq = new byte[16]; 	                // Define o Buffer de Transmissao
            byte[] MsgBinRec = new byte[TamMsgRsp];
            int TamCab = 8;
            MsgReq[0]= 1;

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(EndIP);
            clientSocket.setSoTimeout(2000);
            DatagramPacket sendPacket = new DatagramPacket(MsgReq, TamCab, IPAddress, Porta);

            clientSocket.send(sendPacket);
            String MsgTerm = "Enviada Requisicao Binaria para o Controlador de Água Quente";
            SupService.Terminal(MsgTerm, false, Verbose);
            int EstUTRAQ = 0;
            // Espera a Mensagem Binária de Resposta. Se a mensagem de resposta  for recebida, carrega nas variáveis
            try {
                DatagramPacket receivePacket = new DatagramPacket(MsgBinRec, TamMsgRsp);
                clientSocket.receive(receivePacket);

                //LeEstMeds1(MsgBinRec);  // Carrega as informações recebidas nas variáveis
                MsgTerm = "Recebida Mensagem Binaria do Controlador de Água Quente";
                SupService.Terminal(MsgTerm, false, Verbose);
                EstUTRAQ = 1;
            }
            catch(java.net.SocketTimeoutException e) {
                MsgBinRec[0] = 0x40;
                MsgBinRec[1] = 1;
                MsgBinRec[30] = 0;

                MsgTerm = "Erro: o Controlador de Água Quente nao Respondeu";
                SupService.Terminal(MsgTerm, false, Verbose);
            }
            clientSocket.close();
        }
        catch (IOException err) {
            SupService.Terminal("Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return(TamMsgRsp);
    }

    //******************************************************************************************************************
    // Nome do Método: LeHoraData                                                                                      *
    //                                                                                                                 *
    // Funcao: le a data e hora do relogio do computador usando ZonedDateTime no formato string                        *
    //          "2020-01-01T10:38:17.240-03:00[America/Araguaina]"                                                     *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: array com 6 Bytes: [0] = Hora, [1] = Minuto, [2] = Segundo, [3] = Dia, [4] = Mês, [5] = Ano              *                                                                                 *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static byte[] LeDataHora() {

        LocalDateTime datahora = LocalDateTime.now();
        int Dia = datahora.getDayOfMonth();
        int Mes = datahora.getMonthValue();
        int Ano = datahora.getYear();
        int Anoc = Ano / 100;
        int Anou = Ano - 100 * Anoc;
        int Hora = datahora.getHour();
        int Minuto = datahora.getMinute();
        int Segundo = datahora.getSecond();
        byte DH[] = new byte[7];

        DH[0] = (byte) ByteLow(Hora);         // Hora
        DH[1] = (byte) ByteLow(Minuto);       // Minuto
        DH[2] = (byte) ByteLow(Segundo);      // Segundo
        DH[3] = (byte) ByteLow(Dia);          // Dia
        DH[4] = (byte) ByteLow(Mes);          // Mes
        DH[5] = (byte) ByteLow(Anoc);         // Ano (Unidades)
        DH[6] = (byte) ByteLow(Anou);         // Ano (Centenas)

        return (DH);
    }

    //******************************************************************************************************************
    // Nome do Método: ImprimeHoraData                                                                                 *
    //                                                                                                                 *
    // Funcao: gera uma string com a data e a hora recebida em um array de bytes                                       *
    //                                                                                                                 *
    // Entrada: Array[6] [0] = Hora, [1] = Minutos, [2] = Segundos, [3] = Dia, [4] = Mês, [5] = Ano, [6] = Ano         *
    //          Se Opcao = true imprime a hora e a data / Se Opcao = false imprime só a hora                           *
    //                                                                                                                 *
    // Saida: string no formato HH:MM:SS - DD/MM/AAAA                                                                  *                                                                                 *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String ImprimeHoraData(byte[] DH, boolean Opcao) {

        String Msg = "";
        if (DH[0] < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + DH[0] + ":";
        if (DH[1] < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + DH[1] + ":";
        if (DH[2] < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + DH[2];

        if (Opcao) {
            Msg = Msg + " - ";
            if (DH[3] < 10) {
                Msg = Msg + "0";
            }
            Msg = Msg + DH[3] + "/";
            if (DH[4] < 10) {
                Msg = Msg + "0";
            }
            Msg = Msg + DH[4] + "/" + DH[5] + DH[6];
        }

        return (Msg);
    }

    //******************************************************************************************************************
    // Nome do Método: Terminal                                                                                        *
    //                                                                                                                 *
    // Funcao: imprime uma mensagem no Terminal precedidsa pela hora e a data                                          *
    //                                                                                                                 *
    // Entrada: string com a mensagem, a flag Opcao e a flag de habilitação (Verbose)                                  *
    //          Se Opcao = true imprime a hora e a data / Se Opcao = false imprime só a hora                           *
    //                                                                                                                 *
    // Saida: não tem                                                                                                  *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static void Terminal(String Msg, boolean Opcao, boolean Verbose) {
        if (Verbose) {
            System.out.println(ImprimeHoraData(LeDataHora(), Opcao) + " - " + Msg);
        }
    }

    //******************************************************************************************************************
    // Nome do Método: EntTagValue                                                                                     *
    //                                                                                                                 *
    // Funcao: monta um array de duas strings a partir de duas strings (Tag e Value). Se a flag falha = true,          *
    //         preenche o campo Value com ---------- indicando falha.                                                  *
    //                                                                                                                 *
    // Entrada: string com a Tag, string com o Value e boolean falha                                                   *
    //                                                                                                                 *
    // Saida: array[2] com a string Tag na posição 0 e a string Values na posição 1.                                   *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String[] EntTagValue(String tag, String value, boolean normal) {
        String[] tagvalue = new String[2];
        tagvalue[0] = tag;
        if (normal) {
            tagvalue[1] = value;
        } else {
            tagvalue[1] = "----------";
        }
        return (tagvalue);
    }

    //******************************************************************************************************************
    // Nome da Rotina: BytetoInt                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um valor byte para inteiro (conversao sem sinal)                                               *
    // Entrada: valor byte sem sinal de 0 a 255                                                                        *
    // Saida: valor (inteiro) sempre positivo de 0 a 255                                                               *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int BytetoInt(int valor) {
        if (valor < 0) {
            return (256 + valor);
        } else {
            return (valor);
        }
    }

    //******************************************************************************************************************
    // Nome da Rotina: TwoBytetoInt                                                                                    *
    //                                                                                                                 *
    // Funcao: converte dois bytes em sequencia de um array para inteiro (conversao sem sinal)                         *
    // Entrada: dois bytes consecutivos (LSB e MSB) sem sinal de 0 a 255                                               *
    // Saida: valor (inteiro) sempre positivo de 0 a 65535                                                             *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int DoisBytesInt(int LSByte, int MSByte) {
        int lsb;
        int msb;
        if (LSByte < 0) {
            lsb = LSByte + 256;
        } else {
            lsb = LSByte;
        }
        if (MSByte < 0) {
            msb = MSByte + 256;
        } else {
            msb = MSByte;
        }
        return (lsb + 256 * msb);
    }

    //******************************************************************************************************************
    // Nome da Rotina: ThreeBytetoInt                                                                                  *
    //                                                                                                                 *
    // Funcao: converte tres bytes em sequencia de um array para inteiro (conversao sem sinal)                         *
    // Entrada: dois bytes consecutivos (LSB e MSB) sem sinal de 0 a 255                                               *
    // Saida: valor (inteiro) sempre positivo de 0 a 65535                                                             *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int ThreeBytetoInt(int LSByte, int MSByte, int HSByte) {
        int lsb;
        int msb;
        int hsb;
        if (LSByte < 0) {
            lsb = LSByte + 256;
        } else {
            lsb = LSByte;
        }
        if (MSByte < 0) {
            msb = MSByte + 256;
        } else {
            msb = MSByte;
        }
        if (HSByte < 0) {
            hsb = HSByte + 256;
        } else {
            hsb = HSByte;
        }
        return (lsb + 256 * msb + 65536 * hsb);
    }

    //******************************************************************************************************************
    // Nome da Rotina: FormAnaHora                                                                                     *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo para uma string no formato HH:MM:SS  (hora:minuto:segundo)                 *
    // Entrada: valor inteiro em segundos                                                                              *
    // Saida: string do numero no formato "parte inteira","duas casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String FormAnaHora(int valor) {
        int Hora = valor / 3600;
        int Minuto = ((valor - (Hora * 3600)) / 60);
        int Segundo = valor - (Minuto * 60) - (Hora * 3600);
        String HMS = "";
        if (Hora < 10) {
            HMS = "0";
        }
        HMS = (HMS + Hora + ":");
        if (Minuto < 10) {
            HMS = HMS + "0";
        }
        HMS = (HMS + Minuto + ":");
        if (Segundo < 10) {
            HMS = HMS + "0";
        }
        HMS = HMS + Segundo;

        return (HMS);
    }

    //******************************************************************************************************************
    // Nome da Rotina: CharToByte                                                                                      *
    //                                                                                                                 *
    // Funcao: converte um caracter numerico em um valor numerico de 0 a 9                                             *
    // Entrada: caracter: '0' a '9'                                                                                    *
    // Saida: byte (valor numerico de 0 a 9)                                                                           *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int CharToByte(char caracter) {
        byte Num = 10;
        switch (caracter) {
            case '0':
                Num = 0;
                break;
            case '1':
                Num = 1;
                break;
            case '2':
                Num = 2;
                break;
            case '3':
                Num = 3;
                break;
            case '4':
                Num = 4;
                break;
            case '5':
                Num = 5;
                break;
            case '6':
                Num = 6;
                break;
            case '7':
                Num = 7;
                break;
            case '8':
                Num = 8;
                break;
            case '9':
                Num = 9;
                break;
        }
        return (Num);
    }

    //******************************************************************************************************************
    // Nome da Rotina: CharToInt                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um caracter numerico em um valor numerico de 0 a 9                                             *
    // Entrada: caracter: '0' a '9'                                                                                    *
    // Saida: int (valor numerico de 0 a 9)                                                                            *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int ChToInt(char caracter) {
        int Num = 10;
        switch (caracter) {
            case '0':
                Num = 0;
                break;
            case '1':
                Num = 1;
                break;
            case '2':
                Num = 2;
                break;
            case '3':
                Num = 3;
                break;
            case '4':
                Num = 4;
                break;
            case '5':
                Num = 5;
                break;
            case '6':
                Num = 6;
                break;
            case '7':
                Num = 7;
                break;
            case '8':
                Num = 8;
                break;
            case '9':
                Num = 9;
                break;
        }
        return (Num);
    }

    //******************************************************************************************************************
    // Nome da Rotina: TwoCharToByte                                                                                   *
    //                                                                                                                 *
    // Funcao: converte dois caracteres numericos em um valor numerico de 00 a 99                                      *
    // Entrada: caracteres dezena e unidade ('0' a '9')                                                                *
    // Saida: byte (valor numerico de 00 a 99)                                                                         *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int TwoCharToByte(char Ch10, char Ch1) {
        int Num = 10 * CharToByte(Ch10) + CharToByte(Ch1);
        return ((byte) Num);
    }

    //******************************************************************************************************************
    // Nome da Rotina: TwoCharToInt                                                                                    *
    //                                                                                                                 *
    // Funcao: converte dois caracteres numericos em um valor numerico de 00 a 99                                      *
    // Entrada: caracteres dezena e unidade ('0' a '9')                                                                *
    // Saida: int (valor numerico de 00 a 99)                                                                          *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int TwoCharToInt(char Ch10, char Ch1) {
        int Num = 10 * CharToByte(Ch10) + CharToByte(Ch1);
        return (Num);
    }

    //******************************************************************************************************************
    // Nome da Rotina: FourCharToInt                                                                                   *
    //                                                                                                                 *
    // Funcao: converte quatro caracteres numericos em um valor numerico de 0000 a 9999                                *
    // Entrada: caracteres milhar, centena, dezena e unidade ('0' a '9')                                               *
    // Saida: int (valor numerico de 0000 a 9999)                                                                      *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int FourCharToInt(char Ch1000, char Ch100, char Ch10, char Ch1) {
        int Num = 1000 * CharToByte(Ch1000) + 100 * CharToByte(Ch100) + 10 * CharToByte(Ch10) + CharToByte(Ch1);
        return (Num);
    }

    //******************************************************************************************************************
    // Nome da Rotina: StringToInt                                                                                     *
    //                                                                                                                 *
    // Funcao: converte uma string de até quatro caracteres numericos em um valor numerico de 0000 a 9999              *
    // Entrada: string com um numero entre 0 e 9999                                                                    *
    // Saida: int (valor numerico de 0000 a 9999 correspondente à string de entrada)                                   *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static int StringToInt(String StNm) {
        int Num = 0;
        int TamNum = StNm.length();

        if (TamNum == 1) {
            Num = ChToInt(StNm.charAt(0));
        }
        if (TamNum == 2) {
            Num = 10 * ChToInt(StNm.charAt(0)) + ChToInt(StNm.charAt(1));
        }
        if (TamNum == 3) {
            Num = 100 * ChToInt(StNm.charAt(0)) + 10 * ChToInt(StNm.charAt(1)) + ChToInt(StNm.charAt(2));
        }
        if (TamNum == 4) {
            Num = 1000 * ChToInt(StNm.charAt(0)) + 100 * ChToInt(StNm.charAt(1)) + 10 * ChToInt(StNm.charAt(2)) + ChToInt(StNm.charAt(3));
        }
        return (Num);
    }

    //*****************************************************************************************************************
    // Nome da Rotina: ByteLow                                                                                        *
    //                                                                                                                *
    // Funcao: obtem o byte menos significativo de um valor inteiro                                                   *
    // Entrada: valor inteiro                                                                                         *
    // Saida: byte menos significativo                                                                                *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    public static byte ByteLow(int valor) {
        int BH = valor / 256;
        int BL = valor - 256 * BH;
        return ((byte) BL);
    }


    //*****************************************************************************************************************
    // Nome da Rotina: ByteHigh                                                                                       *
    //                                                                                                                *
    // Funcao: obtem o byte mais significativo de um valor inteiro                                                    *
    // Entrada: valor inteiro                                                                                         *
    // Saida: byte mais significativo                                                                                 *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    public static byte ByteHigh(int valor) {
        int BH = valor / 256;
        return ((byte) BH);
    }

    //******************************************************************************************************************
    // Nome da Rotina: ImpHora                                                                                         *
    //                                                                                                                 *
    // Funcao: gera umna string com hora:minuto:segundo dia/mes/ano                                                    *
    // Entrada: valor hora, minuto, segundo, dia, mes, ano                                                             *
    // Saida: byte mais significativo                                                                                  *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String ImpHora(int hora, int minuto, int segundo) {

        String Msg = "";
        if (hora < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + hora + ":";

        if (minuto < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + minuto + ":";

        if (segundo < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + segundo;

        return (Msg);
    }

    //******************************************************************************************************************
    // Nome da Rotina: ImpData                                                                                         *
    //                                                                                                                 *
    // Funcao: gera umna string com hora:minuto:segundo dia/mes/ano                                                    *
    // Entrada: valor hora, minuto, segundo, dia, mes, ano                                                             *
    // Saida: byte mais significativo                                                                                  *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String ImpData(int dia, int mes, int ano) {

        String Msg = "";
        if (dia < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + dia + "/";

        if (mes < 10) {
            Msg = Msg + "0";
        }
        Msg = Msg + mes + "/" + ano + " ";

        return (Msg);
    }

    //******************************************************************************************************************
    // Nome da Rotina: FormAna                                                                                         *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo em formato x100 para uma string com parte inteira e duas casas decimais    *
    // Entrada: valor inteiro no formato x100                                                                          *
    // Saida: string do numero no formato "parte inteira","duas casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    //public static String FormAna(int valor) {
    //    int inteiro;
    //    int decimal;
    //    inteiro = valor / 100;
    //    decimal = valor - 100*inteiro;
    //    String Valor = (inteiro + ".");
    //    if (decimal > 9) {
    //        Valor = Valor + decimal;
    //    }
    //    else {
    //        Valor = Valor + "0" + decimal;
    //    }
    //    return (Valor);
    //}

    //******************************************************************************************************************
    // Nome da Rotina: FrmAna                                                                                          *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo em formato x100 para uma string com parte inteira e duas casas decimais    *
    // Entrada: valor inteiro no formato x100 e unidade em string                                                      *
    // Saida: string do numero no formato "parte inteira","duas casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String FrmAna(int valor) {
        int inteiro;
        int decimal;
        inteiro = valor / 100;
        decimal = valor - 100 * inteiro;
        String Valor = (inteiro + ".");
        if (decimal > 9) {
            Valor = Valor + decimal;
        } else {
            Valor = Valor + "0" + decimal;
        }
        return (Valor);
    }

    //******************************************************************************************************************
    // Nome da Rotina: FrmAna3                                                                                         *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo em formato x1000 para uma string com parte inteira e tres casas decimais   *
    // Entrada: valor inteiro no formato x1000 e unidade em string                                                     *
    // Saida: string do numero no formato "parte inteira","tres casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String FrmAna3(int valor) {
        int inteiro;
        int decimal;
        inteiro = valor / 1000;
        decimal = valor - 1000 * inteiro;
        String Valor = (inteiro + ".");
        if (decimal > 90) {
            Valor = Valor + decimal;
        } else {
            Valor = Valor + "0" + decimal;
        }
        return (Valor);
    }

    //******************************************************************************************************************
    // Nome da Rotina: FormAna3                                                                                        *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo em formato x1000 para uma string com parte inteira e tres casas decimais   *
    // Entrada: valor inteiro no formato x1000                                                                         *
    // Saida: string do numero no formato "parte inteira","tres casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    //public static String FormAna3(int valor) {
    //    int inteiro;
    //    int decimal;
    //    inteiro = valor / 1000;
    //    decimal = valor - 1000 * inteiro;
    //    String Valor = (inteiro + ".");
    //    if (decimal > 90) {
    //        Valor = Valor + decimal;
    //    }
    //    else {
    //        Valor = Valor + "0" + decimal;
    //    }
    //    return (Valor);
    //}

    //******************************************************************************************************************
    // Nome da Rotina: FormAnaInt                                                                                      *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo para uma string                                                            *
    // Entrada: valor inteiro no formato x100                                                                          *
    // Saida: string do numero no formato "parte inteira","duas casas decimais"                                        *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    //public static String FormAnaInt(int valor) {

    //    String Valor = (valor + "");

    //    return (Valor);
    //}

    //******************************************************************************************************************
    // Nome da Rotina: FrmAnaInt                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo para uma string                                                            *
    // Entrada: valor inteiro                                                                                          *
    // Saida: string do numero no formato inteiro                                                                      *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String FrmAnaInt(int valor) {

        String Valor = (valor + "");

        return (Valor);
    }

    //******************************************************************************************************************
    // Nome da Rotina: IntToStr2                                                                                       *
    //                                                                                                                 *
    // Funcao: converte um inteiro positivo na faixa de 00 a 99 para uma string                                        *
    //                                                                                                                 *
    // Entrada: valor inteiro de 0 a 99                                                                                *
    //                                                                                                                 *
    // Saida: string de dois dígitos do número (formato 00 a 99). Se o valor estiver fora da faixa retorna 00          *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String IntToStr2(int valInt) {
        String valStr = "00";
        if ((valInt >= 0) && (valInt < 100)) {
            if (valInt < 10) {
                valStr = ("0" + valInt);
            } else {
                valStr = (valInt + "");
            }
        }
        return (valStr);
    }

    public static String EstadoSimples(boolean estado, String estadoTrue, String estadoFalse) {
        String strEstado = "";
        if (estado) {
            strEstado = estadoTrue;
        } else {
            strEstado = estadoFalse;
        }
        return strEstado;
    }

    public static String EstFonteEnergia(boolean fonte, boolean habCg, String feTrue, String feFalse, String feStby) {
        String strFe = feFalse;
        if (fonte) {
            strFe = feTrue;
        } else {
            if (habCg) {
                strFe = feStby;
            }
        }
        return strFe;
    }

    public static String EstadoCaixaAzul(byte estado) {
        String estcxaz = "";
        switch (estado) {

            case 0:  //  EstadoCxAz = 0 => Estado da Caixa Azul = Indefinido
                estcxaz = "Indefinido";
                break;

            case 1:  //  EstadoCxAz = 1 => Estado da Caixa Azul = Precisa Encher Nivel Baixo
                estcxaz = "Precisa Encher";
                break;

            case 2:  //  EstadoCxAz = 2 => Estado da Caixa Azul = Precisa Encher Nivel Normal
                estcxaz = "Precisa Encher";
                break;

            case 3:  //  EstadoCxAz = 3 => Estado da Caixa Azul = Cheia
                estcxaz = "Cheia";
                break;

            case 4:  //  EstadoCxAz = 4 => Estado da Caixa Azul = Falha de Sinalizacao 1

            case 5:  // EstadoCxAz = 5 => Estado da Caixa Azul = Falha de Sinalizacao 2
                estcxaz = "Falha Boia";
                break;
        }
        return estcxaz;
    }

    public static String NivelCaixaAzul(byte estado) {
        String nivcxaz = "";
        switch (estado) {

            case 0:  //  EstadoCxAz = 0 => Estado da Caixa Azul = Indefinido
                nivcxaz = "Indefinido";
                break;

            case 1:  //  EstadoCxAz = 1 => Estado da Caixa Azul = Precisa Encher Nivel Baixo
                nivcxaz = "Baixo";
                break;

            case 2:  //  EstadoCxAz = 2 => Estado da Caixa Azul = Precisa Encher Nivel Normal
                nivcxaz = "Normal";
                break;

            case 3:  //  EstadoCxAz = 3 => Estado da Caixa Azul = Cheia
                nivcxaz = "Normal";
                break;

            case 4:  //  EstadoCxAz = 4 => Estado da Caixa Azul = Falha de Sinalizacao 1

            case 5:  // EstadoCxAz = 5 => Estado da Caixa Azul = Falha de Sinalizacao 2
                nivcxaz = "";
                break;
        }
        return nivcxaz;
    }

    public static String EstadoDepRede(boolean estRede, boolean estado, String strTrue, String strFalse) {
        String strEst = "";
        if (estRede) {
            if (estado) {
                strEst = strTrue;
            } else {
                strEst = strFalse;
            }
        } else {
            strEst = "Falta CA";
        }
        return strEst;
    }

    public static String EstadoRede(boolean estRede, int tensaoRede, int limite) {
        String estVrd = "";
        if(estRede) {
            if (tensaoRede > 19000) {
                estVrd = "Normal";
            } else {
                estVrd = "(Baixa)";
            }
        }
        else {
            estVrd = "Falta CA";
        }
        return estVrd;
    }

    public static int CalcEficienciaInversor(int weInv, int wsInv) {
        int EfIv2 = 0;
        if (weInv > 2000) {
            EfIv2 = (100 * wsInv) / weInv;
        }
        else {
            EfIv2 = 0;
        }
        return EfIv2;
    }

    public static String JsonString(String[][] KeyValue, int numObj) {
        String MsgJson = "{\n";
        for (short i = 0; i < numObj; i++) {
            MsgJson = MsgJson + "\"" + KeyValue[i][0].toLowerCase() + "\"" + " : "
                    + "\"" + KeyValue[i][1] + "\"";

            if (i < (numObj - 1)) { MsgJson = MsgJson + ",";}
            MsgJson = MsgJson + "\n";
        }
        MsgJson = MsgJson + " }";

        return MsgJson;
    }

    public static String[] EntKeyValue(String Key, String Value) {
        String[] keyvalue = new String[2];
        keyvalue[0] = Key;
        keyvalue[1] = Value;
        return (keyvalue);
    }
}
