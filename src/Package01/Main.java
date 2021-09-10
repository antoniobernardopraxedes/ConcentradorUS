package Package01;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

//**********************************************************************************************************************
//                                                                                                                     *                                                     *
// Projeto: Atualizador do Servidor em Nuvem                                                                           *
//                                                                                                                     *
// Nome da Classe: Main                                                                                                *
//                                                                                                                     *
// Funcao: efetua a comunicação com o Concentrador em protocolo CoAP e envia as informações para o Servidor na Nuvem   *
//                                                                                                                     *
//**********************************************************************************************************************
//
public class Main {

    public static void main(String[] args) throws IOException {

        boolean Verbose = true;
        boolean MsgJson = true;

        //String EndIPSrv = "200.98.140.180";
        //String EndIPSrv = "192.168.0.49";
        String EndIPSrv = "localhost";
        int PortaSrv = 8080;
        String Recurso = "atualiza";

        String IPConcArd = "192.168.0.150";
        int PortaUDP = 5683;
        int ContMsgCoAP = 0;
        int TamMsgCoAP = 320;

        String Caminho = "/home/";
        //String IPHost = "192.168.0.170";
        String IPHost = "localhost";
        int Comando = 0;

        byte DH [] = new byte[6];
        byte Segundo;
        byte SegundoAnterior;

        DH = Util.LeDataHora();
        Segundo = DH[2];
        SegundoAnterior = Segundo;

        InetAddress ip = InetAddress.getLocalHost();
        String NomeComputador = "";
        NomeComputador = ip.getHostName();
        if (NomeComputador.equals("raspberrypi")) {
            Caminho = "/home/pi/Desktop/Programas/";
            IPHost = "192.168.0.170";

            Util.Terminal("Atualizador Iniciado no Computador Raspberry PI 3", true, true);
        }
        if (NomeComputador.equals("BernardoLinux")) {
            Caminho = "/home/antonio/ExecJava/";
            IPHost = "192.168.0.49";
            Verbose = true;
            Util.Terminal("Atualizador Iniciado no Computador BernardoLinux", true, Verbose);
        }

        int cont = 0;
        boolean fim = false;
        while (!fim) {
            DH = Util.LeDataHora();
            Segundo = DH[2];
            if (Segundo != SegundoAnterior) {
                cont = cont + 1;
                SegundoAnterior = Segundo;
            }
            String MsgRec = "";
            byte[] MsgEnvSrv = new byte[0];
            if (cont >= 4) {

                Socket socket = new Socket(EndIPSrv, PortaSrv);
                socket.setSoTimeout(3000);

                if (socket.isConnected()) {
                    String MsgTerm = "Atualizador conectou ao Socket: " + socket.toString();
                    Util.Terminal(MsgTerm, false, Verbose);
                }

                if (Verbose) System.out.println(" ");
                MsgRec = "";
                MsgEnvSrv = EnvRecMsg.CoAPUDP(IPConcArd, PortaUDP, "estados", ContMsgCoAP, Comando, Verbose);

                if (MsgJson) {
                    String MsgJsonSrv = "{ \"cmd\" : \"001\" }";
                    MsgRec = EnvRecMsg.EnvString(socket, IPHost, MsgJsonSrv, Recurso, Verbose );
                }
                else {
                    MsgRec = EnvRecMsg.BinSrv(socket, IPHost, MsgEnvSrv, Recurso, Verbose);
                }
                Util.Terminal("Mensagem recebida do servidor: " + MsgRec, false, Verbose);
                cont = 0;
                socket.close();
            }

            if (!MsgRec.isEmpty()) {
                String Tk2 = "<CMD>";
                String Tk3 = "</CMD>";
                String Cmd = "";
                int indice1 = 0;
                int indice2 = 0;
                if (MsgRec.contains(Tk2) && MsgRec.contains(Tk3)) {
                    indice1 = MsgRec.indexOf("<CMD>") + 5;
                    indice2 = MsgRec.indexOf("</CMD>");
                    Cmd = MsgRec.substring(indice1, indice2);
                    if (!(Cmd.isEmpty())) {
                        Comando = ExecComandoHTTP(Cmd, Verbose);
                        Util.Terminal("Rec Msg de Comando do Serv: " + Cmd, false, Verbose);
                    }
                    else {
                        Util.Terminal("Rec Msg de Reconhecimento do Serv", false, Verbose);
                    }
                }
                else {
                    Util.Terminal("Rec Msg Desconhecida do Serv", false, Verbose);
                }
            }
        } // while (!fim)
    }


    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: ExecComandoHTTP                                                                                 *
    //	                                                                                                               *
    // Funcao: obtém o número do comando referente à string de comando recebida do Servidor HTTP                       *
    //                                                                                                                 *
    // Entrada: string com o código do comando recebido do Servidor HTTP e a habilitação de impressão de mensagem      *
    //                                                                                                                 *
    // Saida: o número inteiro refetente à string de comando recebida do Servidor HTTP                                 *
    //	                                                                                                               *
    //******************************************************************************************************************
    //
    private static int ExecComandoHTTP(String comrechttp, boolean Verbose) {
        int Comando = 0;
        if (comrechttp.equals("cmd=0002")) {
            Comando = 2;
            Util.Terminal("Comando: Acerto Relogio", false, Verbose);
        }
        if (comrechttp.equals("cmd=0003")) {
            Comando = 3;
            Util.Terminal("Comando: Modo Economia", false, Verbose);
        }
        if (comrechttp.equals("cmd=0004")) {
            Comando = 4;
            Util.Terminal("Comando: Modo Normal", false, Verbose);
        }
        if (comrechttp.equals("cmd=0016")) {
            Comando = 16;
            Util.Terminal("Comando: Manual Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0017")) {
            Comando = 17;
            Util.Terminal("Comando: Automatico Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0005")) {
            Comando = 5;
            Util.Terminal("Comando: Manual Cargas 234", false, Verbose);
        }
        if (comrechttp.equals("cmd=0006")) {
            Comando = 6;
            Util.Terminal("Comando: Automatico Cargas 234", false, Verbose);
        }
        if (comrechttp.equals("cmd=0007")) {
            Comando = 7;
            Util.Terminal("Comando: Habilita Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0008")) {
            Comando = 8;
            Util.Terminal("Comando: Desabilita Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0009")) {
            Comando = 9;
            Util.Terminal("Comando: Habilita Carga 2", false, Verbose);
        }
        if (comrechttp.equals("cmd=0010")) {
            Comando = 10;
            Util.Terminal("Comando: Desabilita Carga 2", false, Verbose);
        }
        if (comrechttp.equals("cmd=0011")) {
            Comando = 11;
            Util.Terminal("Comando: Habilita Carga 3", false, Verbose);
        }
        if (comrechttp.equals("cmd=0012")) {
            Comando = 12;
            Util.Terminal("Comando: Desabilita Carga 3", false, Verbose);
        }
        if (comrechttp.equals("cmd=0013")) {
            Comando = 13;
            Util.Terminal("Comando: Habilita Carga 4", false, Verbose);
        }
        if (comrechttp.equals("cmd=0014")) {
            Comando = 14;
            Util.Terminal("Comando: Desabilita Carga 4", false, Verbose);
        }
        if (comrechttp.equals("cmd=0015")) {
            Comando = 15;
            Util.Terminal("Comando: Apaga Indicadores de Falha", false, Verbose);
        }
        return(Comando);
    }
}
