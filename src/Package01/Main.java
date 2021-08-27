package Package01;
import java.io.IOException;
import java.net.InetAddress;

//**********************************************************************************************************************
//                                                                                                                     *                                                     *
// Projeto: Concentrador Usina Solar                                                                                   *
//                                                                                                                     *
// Nome da Classe: Main                                                                                                *
//                                                                                                                     *
// Funcao: efetua a comunicação com a UTR em protocolo CoAP e envia as informações para o Servidor na Nuvem            *
//                                                                                                                     *
//**********************************************************************************************************************
//
public class Main {

    public static void main(String[] args) throws IOException {

        boolean Verbose = false;

        String EndIPSrv = "200.98.140.180";
        int PortaSrv = 8080;
        String Metodo = "POST";
        String Recurso = "atualiza";

        String IPConcArd = "192.168.0.150";
        int PortaUDP = 5683;
        int ContMsgCoAP = 0;
        int TamMsgCoAP = 320;

        String Caminho = "/home/";
        String IPHost = "192.168.0.170";
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

            Util.Terminal("Concentrador Iniciado no Computador Raspberry PI 3", true, true);
        }
        if (NomeComputador.equals("BernardoLinux")) {
            Caminho = "/home/antonio/ExecJava/";
            IPHost = "192.168.0.49";
            Verbose = true;
            Util.Terminal("Concentrador Iniciado no Computador BernardoLinux", true, Verbose);
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
            if (cont >= 4) {

                byte[] MsgEnvSrv = EnvRecMsg.CoAPUDP(IPConcArd, PortaUDP, "estados", ContMsgCoAP, Comando, Verbose);

                String MsgRec = EnvRecMsg.BinSrv(EndIPSrv, PortaSrv, IPHost, MsgEnvSrv, Metodo, Recurso, Verbose);
                if (!MsgRec.isEmpty()) {
                    String Tk1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
                    String Tk2 = "<CMD>";
                    String Tk3 = "</CMD>";
                    String Cmd = "";
                    int indice1 = 0;
                    int indice2 = 0;
                    if (MsgRec.contains(Tk1) && MsgRec.contains(Tk2) && MsgRec.contains(Tk3)) {
                        indice1 = MsgRec.indexOf("<CMD>") + 5;
                        indice2 = MsgRec.indexOf("</CMD>");
                        Cmd = MsgRec.substring(indice1, indice2);
                        if (!(Cmd.isEmpty())) {
                            Comando = ExecComandoHTTP(Cmd, Verbose);
                            Util.Terminal("Comando Recebido: " + Cmd, false, Verbose);
                        }
                    } else {
                        Util.Terminal("Recebida Mensagem de Resposta Inválida", false, Verbose);
                    }
                    cont = 0;
                } // if (!MsgRec.isEmpty())
            } // if (cont >= 4)
        } // while (true)
    } // public static void main(String[] args) throws IOException


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
