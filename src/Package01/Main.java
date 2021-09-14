package Package01;
import java.net.InetAddress;

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

    public static void main(String[] args) throws Exception {

        boolean Verbose = true;
        boolean MsgJson = true;

        //String EndIPSrv = "200.98.140.180";
        //String EndIPSrv = "192.168.0.49";
        String EndIPSrv = "localhost";
        int PortaSrv = 8080;
        String Recurso = "atualiza";

        String IPConcArd = "192.168.0.150";
        int PortaUDP = 5683;
        int CntMsgCoAP = 0;
        int TamMsgCoAP = 320;

        String Caminho = "/home/";
        //String IPHost = "192.168.0.170";
        String IPHost = "localhost";
        int Comando = 0;

        byte DH [] = new byte[6];
        byte Segundo;
        byte SegundoAnterior;

        DH = SupService.LeDataHora();
        Segundo = DH[2];
        SegundoAnterior = Segundo;

        InetAddress ip = InetAddress.getLocalHost();
        String NomeComputador = "";
        NomeComputador = ip.getHostName();
        if (NomeComputador.equals("raspberrypi")) {
            Caminho = "/home/pi/Desktop/Programas/";
            IPHost = "192.168.0.170";

            SupService.Terminal("Atualizador Iniciado no Computador Raspberry PI 3", true, true);
        }
        if (NomeComputador.equals("BernardoLinux")) {
            Caminho = "/home/antonio/ExecJava/";
            IPHost = "192.168.0.49";
            Verbose = true;
            SupService.Terminal("Atualizador Iniciado no Computador BernardoLinux", true, Verbose);
        }

        int cont = 0;
        boolean fim = false;
        while (!fim) {
            DH = SupService.LeDataHora();
            Segundo = DH[2];
            if (Segundo != SegundoAnterior) {
                cont = cont + 1;
                SegundoAnterior = Segundo;
            }
            String MsgRec = "";
            if (cont >= 4) {
                if (Verbose) System.out.println(" ");
                byte[] MsgRecCoAP = SupService.CoAPUDP(IPConcArd, PortaUDP, "estados", CntMsgCoAP, Comando, Verbose);
                byte[] MsgEnvSrv = Dados001.LeEstMedsPayload(MsgRecCoAP);

                if (MsgJson) {
                    String MsgJsonSrv = Dados001.MontaJson();
                    MsgRec = SupService.JsonSrv(EndIPSrv, PortaSrv, IPHost, MsgJsonSrv, Recurso, Verbose );
                    //System.out.println(MsgJsonSrv);
                }
                else {
                    MsgRec = SupService.BinSrv(EndIPSrv, PortaSrv, IPHost, MsgEnvSrv, Recurso, Verbose);
                }
                cont = 0;
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
                        SupService.Terminal("Rec Msg de Comando do Serv: " + Cmd, false, Verbose);
                    }
                    else {
                        SupService.Terminal("Rec Msg de Reconhecimento do Serv", false, Verbose);
                    }
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
            SupService.Terminal("Comando: Acerto Relogio", false, Verbose);
        }
        if (comrechttp.equals("cmd=0003")) {
            Comando = 3;
            SupService.Terminal("Comando: Modo Economia", false, Verbose);
        }
        if (comrechttp.equals("cmd=0004")) {
            Comando = 4;
            SupService.Terminal("Comando: Modo Normal", false, Verbose);
        }
        if (comrechttp.equals("cmd=0016")) {
            Comando = 16;
            SupService.Terminal("Comando: Manual Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0017")) {
            Comando = 17;
            SupService.Terminal("Comando: Automatico Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0005")) {
            Comando = 5;
            SupService.Terminal("Comando: Manual Cargas 234", false, Verbose);
        }
        if (comrechttp.equals("cmd=0006")) {
            Comando = 6;
            SupService.Terminal("Comando: Automatico Cargas 234", false, Verbose);
        }
        if (comrechttp.equals("cmd=0007")) {
            Comando = 7;
            SupService.Terminal("Comando: Habilita Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0008")) {
            Comando = 8;
            SupService.Terminal("Comando: Desabilita Carga 1", false, Verbose);
        }
        if (comrechttp.equals("cmd=0009")) {
            Comando = 9;
            SupService.Terminal("Comando: Habilita Carga 2", false, Verbose);
        }
        if (comrechttp.equals("cmd=0010")) {
            Comando = 10;
            SupService.Terminal("Comando: Desabilita Carga 2", false, Verbose);
        }
        if (comrechttp.equals("cmd=0011")) {
            Comando = 11;
            SupService.Terminal("Comando: Habilita Carga 3", false, Verbose);
        }
        if (comrechttp.equals("cmd=0012")) {
            Comando = 12;
            SupService.Terminal("Comando: Desabilita Carga 3", false, Verbose);
        }
        if (comrechttp.equals("cmd=0013")) {
            Comando = 13;
            SupService.Terminal("Comando: Habilita Carga 4", false, Verbose);
        }
        if (comrechttp.equals("cmd=0014")) {
            Comando = 14;
            SupService.Terminal("Comando: Desabilita Carga 4", false, Verbose);
        }
        if (comrechttp.equals("cmd=0015")) {
            Comando = 15;
            SupService.Terminal("Comando: Apaga Indicadores de Falha", false, Verbose);
        }
        return(Comando);
    }
}
