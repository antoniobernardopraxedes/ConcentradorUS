package Package01;
import java.io.*;
import java.net.*;
import java.lang.String;
import java.util.Date;
import java.util.Locale;

public class EnvRecMsg {

    private static byte Hora;
    private static byte Minuto;
    private static byte Segundo;
    private static byte Dia;
    private static byte Mes;
    private static byte Ano;
    private static byte EstComUTR;
    private static byte EstComCC1;
    private static byte EstComCC2;
    private static byte EstCom1;

    private static byte DJEINV1;
    private static byte CircBoia;
    private static byte BoiaCxAzul;
    private static byte CircBomba;
    private static byte AlRedeBomba;
    private static byte EstRede;
    private static byte MdOp;
    private static byte MdCom;
    private static byte MdCtrl1;
    private static byte MdCtrl;
    private static byte Carga1;
    private static byte Carga2;
    private static byte Carga3;
    private static byte Carga4;
    private static byte HabCom;
    private static byte EstadoInversor1;
    private static byte EstadoInversor2;
    private static byte EstadoCarga3;
    private static byte BombaLigada;

    private static byte FalhaIv1;
    private static byte SubTensaoInv1;
    private static byte SobreTensaoInv1;
    private static byte SobreTempDrInv1;
    private static byte SobreTempTrInv1;
    private static byte DjAbIv1;
    private static byte FalhaIv2;
    private static byte SubTensaoInv2;
    private static byte SobreTensaoInv2;
    private static byte SobreTempDrInv2;
    private static byte SobreTempTrInv2;
    private static byte DjAbIv2;

    private static byte CDBat;
    private static byte CxAzNvBx;
    private static byte EdCxAzCheia;
    private static byte FonteCC2Ligada;
    private static byte EstadoCxAz;
    private static byte FonteCC1Ligada;

    private static byte SobreCorrInv1;
    private static byte SobreCorrInv2;

    private static byte Iv1Lig;
    private static byte CT2Inv;
    private static byte CT1Inv;
    private static byte CT3Inv;
    private static byte Iv2Lig;
    private static byte EstFonteCC;

    private static int VBat;        // Tensão do Banco de Baterias
    private static int VMBat;       // Tensão Média Estendida do Banco de Baterias
    private static int VRede;       // Tensão da Rede
    private static int Icarga3;     // Corrente Carga 3 (Geladeira)
    private static int ICircCC;     // Corrente Total dos Circuitos CC
    private static int IFonteCC;    // Corrente de Saída da Fonte CC

    private static int TmpBmbLig;   // Tempo da Bomba Ligada
    private static int TmpCxAzNvBx; // Tempo da Caixa Azul em Nivel Baixo

    private static int VP12;        // 0x3100 - PV array voltage 1
    private static int IS12;        // 0x3101 - PV array current 1
    private static int WS12;        // 0x3102 - PV array power 1
    private static int VBat1;       // 0x3104 - Battery voltage 1
    private static int ISCC1;       // 0x3105 - Battery charging current 1
    private static int WSCC1;       // 0x3106 - Battery charging power 1
    private static int TBat;        // 0x3110 - Battery Temperature 1

    private static int VP34;        // 0x3100 - PV array voltage 2
    private static int IS34;        // 0x3101 - PV array current 2
    private static int WS34;        // 0x3102 - PV array power 2
    private static int VBat2;       // 0x3104 - Battery voltage 2
    private static int ISCC2;       // 0x3105 - Battery charging current 2
    private static int WSCC2;       // 0x3106 - Battery charging power 2 (VG.Med[45])

    private static int IEIv1;       // Corrente de Entrada do Inversor 1 (15)
    private static int WEIv1;		// Potência de Entrada do Inversor 1 (VG.Med[41])
    private static int VSIv1;       // Tensão de Saída do Inversor 1
    private static int ISInv1;      // Corrente de Saída do Inversor 1 (13)
    private static int WSInv1;		// Potencia de Saida do Inversor 1 (VG.Med[42])
    private static int TDInv1;      // Temperatura do Driver do Inversor 1 (2)
    private static int TTInv1;      // Temperatura do Transformador do Inversor 1 (7)
    private static int EfIv1;
    private static int SDIv1;

    private static int IEIv2; 		// Corrente de Entrada do Inversor 2 (12)
    private static int WEIv2;       // Potencia de Entrada do Inversor 2 (VG.Med[38])
    private static int VSIv2;       // Tensão de Saída do Inversor 2
    private static int ISInv2;
    private static int WSInv2;      // Potencia de Saida do Inversor 2 (VG.Med[39])
    private static int TDInv2;      // Temperatura do Driver do Inversor 2 (8)
    private static int TTInv2;      // Temperatura do Transformador do Inversor 2 (9)
    private static int EfIv2;
    private static int SDIv2;

    private static int ITotGer;     // Corrente Total Gerada
    private static int WCircCC;     // Potencia Consumida pelos Circuitos de 24Vcc
    private static int WFonteCC;    // Potencia Fornecida pela Fonte 24Vcc
    private static int IBat;        // Corrente de Carga ou Descarga do Banco de Baterias
    private static int WBat;		// Potência de Carga/Descarga do Banco de Baterias
    private static int WTotGer;		// Potência Total Gerada
    private static int ITotCg;	    // Corrente Total Consumida pelas Cargas
    private static int WTotCg;		// Potência Total Consumida pelas Cargas

    private static int SDCC1;
    private static int SDCC2;
    private static int SDBat;

    private static int EstadoBombaAQ;		 // Estado da Bomba de Água Quente
    private static int EstadoAquecedor;	     // Estado do Aquecedor do Boiler

    private static int TemperaturaBoiler; 	 // Temperatura do Boiler
    private static int TemperaturaPlaca; 	 // Temperatura da Placa Solar
    private static int TempoBmbLigada; 	     // Tempo da Bomba Ligada
    private static int TempoBmbDesligada;    // Tempo da Bomba Desligada

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome da Rotina: MontaJson                                                                                       *
    //                                                                                                                 *
    // Funcao: monta uma mensagem em formato Json a partir das variáveis de supervisão e carrega em uma string         *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: String com a mensagem em formato Json                                                                    *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static String MontaJson() {
        String MsgJson = "";

        // Estados de Comunicacao
        String StrEstCom1 = "Falha";
        if (EstCom1 == 1) { StrEstCom1 = "Normal"; }

        String StrEstComUTR = "Falha";
        if (EstComUTR == 1) { StrEstComUTR = "Normal"; }

        String StrEstComCC1 = "Falha";
        if (EstComCC1 == 1) { StrEstComCC1 = "Normal"; }

        String StrEstComCC2 = "Falha";
        if (EstComCC2 == 1) { StrEstComCC2 = "Normal"; }

        // Estados Gerais
        String StrMdOp = "Economia";
        if (MdOp == 1) { StrMdOp = "Normal"; }

        String StrMdCom = "Local";
        if (MdCom == 1) { StrMdCom = "Remoto"; }

        String StrMdCtrl1 = "Manual";
        if (MdCtrl1 == 1) { StrMdCtrl1 = "Automatico"; }

        String StrMdCtrl = "Manual";
        if (MdCtrl == 1) { StrMdCtrl = "Automatico"; }

        String StrCT2Inv = "Rede";                // Fonte de Energia Carga 1
        if (CT2Inv == 1) {
            StrCT2Inv = "Inversor 2";
        }
        else {
            if (Carga1 == 1) { StrCT2Inv = "Rede (Hab)"; }
        }

        String StrCT1Inv = "Rede";                // Fonte de Energia Carga 2
        if (CT1Inv == 1) { StrCT1Inv = "Inversor 2"; }
        else { if (Carga2 == 1) { StrCT1Inv = "Rede (Hab)"; } }

        String StrCT3Inv = "Rede";                // Fonte de Energia Carga 3
        if (CT3Inv == 1) { StrCT3Inv = "Inversor 2"; }
        else { if (Carga3 == 1) { StrCT3Inv = "Rede (Hab)"; } }

        String StrEstCxAzul = "";
        String StrNivCxAzul = "";
        switch (EstadoCxAz) {

            case 0:  //  EstadoCxAz = 0 => Estado da Caixa Azul = Indefinido
                StrEstCxAzul = "Indefinido";
                StrNivCxAzul = "Indefinido";
                break;

            case 1:  //  EstadoCxAz = 1 => Estado da Caixa Azul = Precisa Encher Nivel Baixo
                StrEstCxAzul = "Precisa Encher";
                StrNivCxAzul = "Baixo";
                break;

            case 2:  //  EstadoCxAz = 2 => Estado da Caixa Azul = Precisa Encher Nivel Normal
                StrEstCxAzul = "Precisa Encher";
                StrNivCxAzul = "Normal";
                break;

            case 3:  //  EstadoCxAz = 3 => Estado da Caixa Azul = Cheia
                StrEstCxAzul = "Cheia";
                StrNivCxAzul = "Normal";
                break;

            case 4:  //  EstadoCxAz = 4 => Estado da Caixa Azul = Falha de Sinalizacao 1

            case 5:  // EstadoCxAz = 5 => Estado da Caixa Azul = Falha de Sinalizacao 2
                StrEstCxAzul = "Falha Boia";
                StrNivCxAzul = "";
                break;
        }

        String StrEstAlimBoia = "";
        if (CircBoia == 1) { StrEstAlimBoia = "Ligado"; }
        else { StrEstAlimBoia = "Desligado"; }

        String StrAlRedeBomba = "";
        if (EstRede == 1) {
            if (AlRedeBomba == 1) { StrAlRedeBomba = "Ligado"; }
            else { StrAlRedeBomba = "Desligado"; }
        }
        else {
            StrAlRedeBomba = "Falta CA";
        }

        String StrIv1Lig = "Rede"; 		// Fonte de energia da bomba
        if (Iv1Lig == 1) {
            StrIv1Lig = "Inversor 1";
        }
        else {
            if (Carga4 == 1) {
                StrIv1Lig = "Rede (Hab)";
            }
        }

        // Estado da alimentação da bomba
        String StrEstBomba = "Desligada";
        if (CircBomba == 1) { StrEstBomba = "Ligada"; }

        // Estado das Fontes CC1 e CC2
        String StrEstFonteCC1 = "";
        String StrEstFonteCC2 = "";
        if (EstRede == 1) {                 	     // Se a tensao da Rede esta OK,
            if (FonteCC1Ligada == 1) {      	     // e se a fonte CC1 está fornecendo tensão,
                StrEstFonteCC1 = "Ligada";     	     // Carrega a mensagem de que a fonte CC1 está ligada
            }
            else {                             	     // Se a fonte CC1 não está fornecendo tensão,
                StrEstFonteCC1 = "Desligada";  	     // Carrega a mensagem de que a fonte CC1 está desligada
            }
            if (FonteCC2Ligada == 1) {      	     // e se a fonte CC2 está fornecendo tensão,
                StrEstFonteCC2 = "Ligada";     	     // Carrega a mensagem de que a fonte CC1 está ligada
            }
            else {                             	     // Se a fonte CC1 não está fornecendo tensão,
                StrEstFonteCC2 = "Desligada";  	     // Carrega a mensagem de que a fonte CC1 está desligada
            }
        }
        else {                                 	     // Se falta CA,
            if (FonteCC1Ligada == 1) {            	 // e se a saida da fonte está sem tensao,
                StrEstFonteCC1 = "Falta CA";   	     // Carrega a mensagem de que Falta CA
            }
            else {
                StrEstFonteCC1 = "Falha";      	     // Carrega a mensagem de Falha
            }
            if (FonteCC2Ligada == 1) {      	             // e se a saida da fonte está sem tensao,
                StrEstFonteCC2 = "Falta CA";   	     // Carrega a mensagem de que Falta CA
            }
            else {
                StrEstFonteCC2 = "Falha";      	     // Carrega a mensagem de Falha
            }
        }

        // Estados do Inversor 2
        String StrEstIv2 = "Desligado";
        String StrEstVSIv2 = "      ";
        if (Iv2Lig == 1) {
            StrEstIv2 = "Ligado";
            if (VSIv2 < 21000) { StrEstVSIv2 = "Baixa"; }
            if ((VSIv2 >= 21000) && (VSIv2 <= 22500)) { StrEstVSIv2 = "Normal"; }
            if (VSIv2 > 22500) { StrEstVSIv2 = "Alta"; }
        }
        else {
            IEIv2 = 0;
            WEIv2 = 0;
            ISInv2 = 0;
            WSInv2 = 0;
        }

        String StrEstTDIv2 = "          ";
        if (TDInv2 < 4600) { StrEstTDIv2 = "Normal"; }
        if ((TDInv2 >= 4600) && (TDInv2 < 5000)) { StrEstTDIv2 = "Alta"; }
        if (TDInv2 >= 5000) { StrEstTDIv2 = "Muito Alta"; }

        String StrEstTTIv2 = "          ";
        if (TTInv2 < 4600) { StrEstTTIv2 = "Normal"; }
        if ((TTInv2 >= 4600) && (TTInv2 < 5000)) { StrEstTTIv2 = "Alta"; }
        if (TTInv2 >= 5000) { StrEstTTIv2 = "Muito Alta"; }

        // EStados do Inversor 1
        String StrEstIv1 = "Desligado";
        String StrEstVSIv1 = "      ";
        if (Iv1Lig == 1) {
            StrEstIv1 = "Ligado";
            if (VSIv1 < 17500) { StrEstVSIv1 = "Baixa"; }
            if ((VSIv1 >= 17500) && (VSIv1 <= 20000)) { StrEstVSIv1 = "Normal"; }
            if (VSIv1 > 20000) { StrEstVSIv1 = "Alta"; }
        }
        else {
            IEIv1 = 0;
            WEIv1 = 0;
            ISInv1 = 0;
            WSInv1 = 0;
        }

        String CorTDIv2 = "";
        if (TDInv2 >= 5000) { CorTDIv2 = "style='color:red;'"; }
        String CorTTIv2 = "";
        if (TTInv2 >= 5000) { CorTTIv2 = "style='color:red;'"; }

        String CorTDIv1 = "";
        if (TDInv1 >= 5000) { CorTDIv1 = "style='color:red;'"; }
        String CorTTIv1 = "";
        if (TTInv1 >= 5000) { CorTTIv1 = "style='color:red;'"; }

        // Estado da Tensão da Rede
        String StrEstRede = "";
        if (EstRede == 1) {
            if (VRede > 19000) { StrEstRede = "Normal"; } else { StrEstRede = "(Baixa)"; }
        }
        else { StrEstRede = "Falta CA"; }

        // Estados da Carga 3 (Geladeira)
        String StrEstValCg3 = "         ";
        if (Icarga3 < 100) { StrEstValCg3 = "Deslig"; }
        if (Icarga3 > 400) { StrEstValCg3 = "Ligada"; }

        String StrEstValVBat = "           ";
        if (VBat < 2300) { StrEstValVBat = "Baixa"; }
        if ((VBat >= 2300) && (VBat < 2640)) { StrEstValVBat = "Carga/Desc.";	}
        if ((VBat >= 2640) && (VBat <= 2760)) { StrEstValVBat = "Flutuação"; }
        if ((VBat > 2760) && (VBat < 2900)) { StrEstValVBat = "Equalização"; }
        if (VBat > 2900) { StrEstValVBat = "Alta"; }

        // Estados da Bateria
        String StrEstIBat = "        ";
        if (CDBat == 1) { StrEstIBat = "Descarga"; } else { StrEstIBat = "Carga"; }

        String CorTBat = "";
        if (TBat > 4000) { CorTBat = "style='color:red;'"; }

        String StrSaudeBat = "Normal";
        if (SDBat < 85) { StrSaudeBat = "Atenção"; }

        // Estados da Tensão dos Paineis
        String StrValVP12 = "      ";
        if (VP12 < 3000) { StrValVP12 = "Baixa"; } else  { StrValVP12 = "Normal"; }

        String StrValVP34 = "      ";
        if (VP34 < 3000) { StrValVP34 = "Baixa"; } else { StrValVP34 = "Normal"; }

        // Montagem da Mensagem Json
        short numObj = 66;
        String[][] KeyValue = new String[numObj][2];

        // Estados de Comunicação
        KeyValue[0] = EntKeyValue("COMCNV", "Normal");
        KeyValue[1] = EntKeyValue("COMCNC", StrEstCom1);
        KeyValue[2] = EntKeyValue("COMUTR", StrEstComUTR);
        KeyValue[3] = EntKeyValue("COMCC1", StrEstComCC1);
        KeyValue[4] = EntKeyValue("COMCC2", StrEstComCC2);

        // Estados e Medidas Gerais
        KeyValue[5] = EntKeyValue("CLK", Util.ImpHora(Hora, Minuto, Segundo));
        KeyValue[6] = EntKeyValue("DATA", Util.ImpData(Dia, Mes, Ano));
        KeyValue[7] = EntKeyValue("CMDEX", "Atualiza");
        KeyValue[8] = EntKeyValue("MDOP", StrMdOp);
        KeyValue[9] = EntKeyValue("MDCOM", StrMdCom);
        KeyValue[10] = EntKeyValue("MDCT1", StrMdCtrl1);
        KeyValue[11] = EntKeyValue("MDCT234", StrMdCtrl);
        KeyValue[12] = EntKeyValue("ENCG1", StrCT2Inv);
        KeyValue[13] = EntKeyValue("ENCG2", StrCT1Inv);
        KeyValue[14] = EntKeyValue("ENCG3", StrCT3Inv);
        KeyValue[15] = EntKeyValue("ICG3", Util.FrmAna3(Icarga3));
        KeyValue[16] = EntKeyValue("VBAT", Util.FrmAna(VBat));
        KeyValue[17] = EntKeyValue("VREDE", Util.FrmAna(VRede));
        KeyValue[18] = EntKeyValue("ESTVRD", StrEstRede);
        KeyValue[19] = EntKeyValue("TBAT", Util.FrmAna(TBat));
        KeyValue[20] = EntKeyValue("SDBAT", Util.FrmAnaInt(SDBat));

        // Estados e Medidas da Caixa d'Água e Bomba (7 Variáveis)
        KeyValue[21] = EntKeyValue("ESTCXAZ", StrEstCxAzul);
        KeyValue[22] = EntKeyValue("NIVCXAZ", StrNivCxAzul);
        KeyValue[23] = EntKeyValue("ESTBMB", StrEstBomba);
        KeyValue[24] = EntKeyValue("ESTDJB", StrEstAlimBoia);
        KeyValue[25] = EntKeyValue("ESTDJRB", StrAlRedeBomba);
        KeyValue[26] = EntKeyValue("ENBMB", StrIv1Lig);
        KeyValue[27] = EntKeyValue("TMPBL", Util.FormAnaHora(TmpBmbLig));

        // Geração Solar e Consumo (18 variáveis)
        KeyValue[28] = EntKeyValue("VP12", Util.FrmAna(VP12));
        KeyValue[29] = EntKeyValue("IS12", Util.FrmAna(IS12));
        KeyValue[30] = EntKeyValue("ISCC1", Util.FrmAna(ISCC1));
        KeyValue[31] = EntKeyValue("WSCC1", Util.FrmAna(WSCC1));
        KeyValue[32] = EntKeyValue("SDCC1", Util.FrmAnaInt(SDCC1));
        KeyValue[33] = EntKeyValue("VP34", Util.FrmAna(VP34));
        KeyValue[34] = EntKeyValue("IS34", Util.FrmAna(IS34));
        KeyValue[35] = EntKeyValue("ISCC2", Util.FrmAna(ISCC2));
        KeyValue[36] = EntKeyValue("WSCC2", Util.FrmAna(WSCC2));
        KeyValue[37] = EntKeyValue("SDCC2", Util.FrmAnaInt(SDCC2));
        KeyValue[38] = EntKeyValue("ITOTGER", Util.FrmAna(ITotGer));
        KeyValue[39] = EntKeyValue("WTOTGER", Util.FrmAna(WTotGer));
        KeyValue[40] = EntKeyValue("ITOTCG", Util.FrmAna(ITotCg));
        KeyValue[41] = EntKeyValue("WTOTCG", Util.FrmAna(WTotCg));
        KeyValue[42] = EntKeyValue("ESTFT1", StrEstFonteCC1);
        KeyValue[43] = EntKeyValue("ESTFT2", StrEstFonteCC2);
        KeyValue[44] = EntKeyValue("ICIRCC", Util.FrmAna3(ICircCC));
        KeyValue[45] = EntKeyValue("WCIRCC", Util.FrmAna(WCircCC));

        // Informação do Inversor 2 (10 Variáveis)
        KeyValue[46] = EntKeyValue("ESTIV2", StrEstIv2);
        KeyValue[47] = EntKeyValue("IEIV2", Util.FrmAna(IEIv2));
        KeyValue[48] = EntKeyValue("WEIV2", Util.FrmAna(WEIv2));
        KeyValue[49] = EntKeyValue("VSIV2", Util.FrmAna(VSIv2));
        KeyValue[50] = EntKeyValue("ISIV2", Util.FrmAna3(ISInv2));
        KeyValue[51] = EntKeyValue("WSIV2", Util.FrmAna(WSInv2));
        KeyValue[52] = EntKeyValue("TDIV2", Util.FrmAna(TDInv2));
        KeyValue[53] = EntKeyValue("TTIV2", Util.FrmAna(TTInv2));
        KeyValue[54] = EntKeyValue("EFIV2", Util.FrmAnaInt(EfIv2));
        KeyValue[55] = EntKeyValue("SDIV2", Util.FrmAnaInt(SDIv2));

        // Informação do Inversor 1 (10 Variáveis)
        KeyValue[56] = EntKeyValue("ESTIV1", StrEstIv2);
        KeyValue[57] = EntKeyValue("IEIV1", Util.FrmAna(IEIv1));
        KeyValue[58] = EntKeyValue("WEIV1", Util.FrmAna(WEIv1));
        KeyValue[59] = EntKeyValue("VSIV1", Util.FrmAna(VSIv1));
        KeyValue[60] = EntKeyValue("ISIV1", Util.FrmAna3(ISInv1));
        KeyValue[61] = EntKeyValue("WSIV1", Util.FrmAna(WSInv1));
        KeyValue[62] = EntKeyValue("TDIV1", Util.FrmAna(TDInv1));
        KeyValue[63] = EntKeyValue("TTIV1", Util.FrmAna(TTInv1));
        KeyValue[64] = EntKeyValue("EFIV1", Util.FrmAnaInt(EfIv1));
        KeyValue[65] = EntKeyValue("SDIV1", Util.FrmAnaInt(SDIv1));

        MsgJson = "{\n";
        for (short i = 0; i < numObj; i++) {
            MsgJson = MsgJson + "\"" + KeyValue[i][0].toLowerCase(Locale.ROOT) + "\"" + " : "
                              + "\"" + KeyValue[i][1] + "\"";

            if (i < (numObj - 1)) { MsgJson = MsgJson + ",";}
            MsgJson = MsgJson + "\n";
        }
        MsgJson = MsgJson + " }";

        return MsgJson;
    }

    private static String[] EntKeyValue(String Key, String Value) {
        String[] keyvalue = new String[2];
        keyvalue[0] = Key;
        keyvalue[1] = Value;
        return (keyvalue);
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
                Util.Terminal(MsgTerm, false, Verbose);
            }
            EnvByte = new BufferedOutputStream(socket.getOutputStream());
            EnvChar = new PrintWriter(EnvByte, true);
            RecChar = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int TamMsgBin = ByteBuf.length;
            String CabXML = Metodo + " /" + Recurso + " HTTP/1.1\r\n";
            CabXML = CabXML + "Host: " + IPHost + ":8080\r\n";
            CabXML = CabXML + "Content-Length: " + TamMsgBin + "\r\n";
            CabXML = CabXML + "Content-Type: application/octet-stream\r\n";
            CabXML = CabXML + "User-Agent: (Linux x86_64) PraxClient/1.0\r\n";
            CabXML = CabXML + "\r\n";

            // Transmite a mensagem para o Servidor
            EnvChar.print(CabXML);
            EnvChar.flush();
            EnvByte.write(ByteBuf, 0, TamMsgBin);
            EnvByte.flush();

            String MsgTrm = "Enviada Requisicao: " + Metodo + " /" + Recurso + " HTTP/1.1 com " + TamMsgBin + " Bytes";
            Util.Terminal(MsgTrm, false, Verbose);
            MsgTrm = " com " + TamMsgBin + " Bytes";

            try {
                String linha;
                while ((linha = RecChar.readLine()) != null) {
                    MsgRec = MsgRec + linha + "\n";
                }
            }
            catch(java.net.SocketTimeoutException tmo) {
                Util.Terminal("Timeout na resposta do Servidor", false, Verbose);
            }
            socket.close();
        }
        catch (IOException err) {
            Util.Terminal(" - Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return(MsgRec);
    }

    //*****************************************************************************************************************
    //                                                                                                                *
    // Nome do Método: Json                                                                                           *
    //	                                                                                                              *
    // Funcao: envia para o servidor conectado uma mensagem em formato Json lida de uma String e espera a resposta    *
    //                                                                                                                *
    // Entrada: Endereço IP e POrta para conexão no Servidor, String com a Mensagem a ser Enviada; String com o       *
    //          Recurso e boolean Verbose (habilita envio de mensagens para o terminal)                               *
    //                                                                                                                *
    // Saida: string com a mensagem de resposta do servidor                                                           *
    //	                                                                                                              *
    //*****************************************************************************************************************
    //
    public static String JsonSrv(String IPSrv, int Porta, String IPHost, String Msg, String Recurso, boolean Verbose) {

        PrintWriter out = null;
        PrintWriter EnvChar = null; BufferedOutputStream EnvByte = null;
        InputStreamReader RecByte = null; BufferedReader RecChar = null;

        String MsgRec = "";

        try {
            Socket socket = new Socket(IPSrv, Porta);
            socket.setSoTimeout(5000);

            if (socket.isConnected()) {
                String MsgTerm = "O Cliente Atualizador conectou ao " + socket.toString();
                Util.Terminal(MsgTerm, false, Verbose);
            }
            out = new PrintWriter(socket.getOutputStream());
            EnvByte = new BufferedOutputStream(socket.getOutputStream());
            EnvChar = new PrintWriter(EnvByte, true);
            RecChar = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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

            Util.Terminal("Enviada Mensagem Json com " + TamMsg + " Caracteres", false, Verbose);

            try {
                String linha;
                while ((linha = RecChar.readLine()) != null) {
                    MsgRec = MsgRec + linha + "\n";
                }
                Util.Terminal("Mensagem Recebida do Servidor: " + MsgRec, false, Verbose);
            }
            catch(java.net.SocketTimeoutException tmo) {
                Util.Terminal("Timeout na resposta do Servidor", false, Verbose);
                socket.close();
            }
            socket.close();
        }
        catch (IOException ioe) {
            Util.Terminal("Erro ao enviar a mensagem HTTP para o Servidor", false, Verbose);
        }
        return MsgRec;

    } // Fim do Método


    //*****************************************************************************************************************
    //                                                                                                                *
    // Nome do Método: EnvStringErro                                                                                  *
    //	                                                                                                              *
    // Funcao: envia para o cliente conectado uma mensagem de erro HTTP lida de uma String                            *
    //                                                                                                                *
    // Entrada: Socket de conexão, int com o código do erro (404 ou 501), boolean Verbose                             *
    //          (habilita envio de mensagens para o terminal)                                                         *
    //                                                                                                                *
    // Saida: se a mensagem foi enviada corretamente, retorna true                                                    *
    //	                                                                                                              *
    //*****************************************************************************************************************
    //
    public static boolean EnvStringErro(Socket connect, int Erro, boolean Verbose) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(connect.getOutputStream());
            String LinhaInicial = "";
            String MsgErro = "";
            String Tipo = "text/html";
            if (Erro == 404) {
                LinhaInicial = "HTTP/1.1 404 File Not Found";
                MsgErro = "<h2>404 File Not Found</h2><h3>HTTP/1.1 PraxServer</h3>";
            }

            if (Erro == 501) {
                LinhaInicial = "HTTP/1.1 501 Not Implemented";
                MsgErro = "<h2>501 Not Implemented</h2><h3>HTTP/1.1 PraxServer</h3>";
            }
            int TamMsg = MsgErro.length();
            out.println(LinhaInicial);
            out.println("Server: Java HTTP Server from PraxServer : 1.0");
            out.println("Date: " + new Date());
            out.println("Content-type: " + Tipo);
            out.println("Content-length: " + TamMsg);
            out.println();
            out.print(MsgErro);
            out.flush();

            Util.Terminal("Enviada Mensagem de Erro: " + LinhaInicial, false, Verbose);

            return(true);
        }
        catch (IOException ioe) {
            Util.Terminal("Erro ao enviar a mensagem de erro lida de uma string", false, Verbose);
            return(false);
        }
    }  // Fim do Método


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
    static byte[] CoAPUDP(String EndIP, int Porta, String URI, int ContMsgCoAP, int Comando, boolean Verbose) {
        int TamMsgRspCoAP = 320;
        int TamMsgSrv = 320;
        byte [] MsgRecCoAP = new byte[TamMsgRspCoAP];
        byte [] MsgEnvSrv = new byte[TamMsgSrv];

        try {
            byte[] MsgReqCoAP = new byte[32];

            int TamURI = URI.length();
            byte DH[] = new byte[6];
            DH = Util.LeDataHora();

            MsgReqCoAP[0] = 0x40;                       // Versão = 01 / Tipo = 00 / Token = 0000
            MsgReqCoAP[1] = 0x01;                       // Código de Solicitação: 0.01 GET
            ContMsgCoAP = ContMsgCoAP + 1;              // Incrementa o Identificador de mensagens
            MsgReqCoAP[2] = Util.ByteHigh(ContMsgCoAP); // Byte Mais Significativo do Identificador da Mensagem
            MsgReqCoAP[3] = Util.ByteLow(ContMsgCoAP);  // Byte Menos Significativo do Identificador da Mensagem
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
            DatagramPacket sendPacket = new DatagramPacket(MsgReqCoAP, TamCab, IPAddress, Porta);
            DatagramPacket receivePacket = new DatagramPacket(MsgRecCoAP, TamMsgRspCoAP);

            clientSocket.send(sendPacket);
            Util.Terminal("Enviada Requisicao CoAP para o Concentrador", false, Verbose);

            // Espera a Mensagem CoAP de Resposta. Se a mensagem de resposta  for recebida, carrega nas variáveis
            try {
                clientSocket.receive(receivePacket);
                MsgRecCoAP[30] = 1;
                MsgEnvSrv = LeEstMedsPayload(MsgRecCoAP, TamMsgRspCoAP);
                Util.Terminal("Recebida Mensagem CoAP do Concentrador", false, Verbose);
            } catch (java.net.SocketTimeoutException e) {
                MsgRecCoAP[0] = 0x40;
                MsgRecCoAP[1] = 1;
                MsgRecCoAP[30] = 0;
                Util.Terminal(" - Erro: o Concentrador nao Respondeu " + MsgRecCoAP[14], false, Verbose);
            }
            clientSocket.close();
        } catch (IOException err) {
            Util.Terminal("Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return (MsgEnvSrv);
    }

    //*****************************************************************************************************************
    // Nome da Rotina: LeEstMedsPayload()                                                                             *
    //                                                                                                                *
    // Funcao: lê as informações dos estados e medidas recebidas do Concentrador Arduino Mega na mensagem em          *
    //         formato CoAP-OSA-CBM                                                                                   *
    //                                                                                                                *
    // Medidas (64): bytes 160 a 288 - 2 bytes por medida                                                             *
    //                                                                                                                *
    // Entrada: array de bytes com a mensagem binária em protocolo CoAP recebida e o núm. de bytes da mensagem        *
    //                                                                                                                *
    // Saida: array de bytes com a mensagem binária a ser enviada para o Servidor HTTP                                *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    static byte[] LeEstMedsPayload(byte[] MsgRecCoAP, int TamMsgSrv) {

        byte[] MsgEnvSrv = new byte[TamMsgSrv];  // Array com a mensagem que vai para o Servidor HTTP

        // Le as Informações de Estado do Concentrador Arduino Mega
        Hora = MsgRecCoAP[21];
        Minuto = MsgRecCoAP[22];
        Segundo = MsgRecCoAP[23];
        Dia = MsgRecCoAP[24];
        Mes = MsgRecCoAP[25];
        Ano = MsgRecCoAP[26];
        EstComUTR = MsgRecCoAP[27];
        EstComCC1 = MsgRecCoAP[28];
        EstComCC2 = MsgRecCoAP[29];
        EstCom1 = MsgRecCoAP[30];

        // Le as Entradas Digitais recebidas na mensagem recebida do Concentrador Arduino Mega
        DJEINV1 = MsgRecCoAP[37];
        CircBoia = MsgRecCoAP[38];
        BoiaCxAzul = MsgRecCoAP[39];
        CircBomba = MsgRecCoAP[40];
        AlRedeBomba = MsgRecCoAP[41];
        EstRede = MsgRecCoAP[42];
        MdOp = MsgRecCoAP[43];
        MdCom = MsgRecCoAP[44];
        MdCtrl1 = MsgRecCoAP[55];
        MdCtrl = MsgRecCoAP[45];
        Carga1 = MsgRecCoAP[46];
        Carga2 = MsgRecCoAP[47];
        Carga3 = MsgRecCoAP[48];
        Carga4 = MsgRecCoAP[49];
        HabCom = MsgRecCoAP[50];
        EstadoInversor1 = MsgRecCoAP[51];
        EstadoInversor2 = MsgRecCoAP[52];
        EstadoCarga3 = MsgRecCoAP[53];
        BombaLigada = MsgRecCoAP[54];

        // Le os Alarmes da mensagem recebida do Concentrador Arduino Mega
        FalhaIv1 = MsgRecCoAP[56];
        SubTensaoInv1 = MsgRecCoAP[57];
        SobreTensaoInv1 = MsgRecCoAP[58];
        SobreTempDrInv1 = MsgRecCoAP[59];
        SobreTempTrInv1 = MsgRecCoAP[60];
        DjAbIv1 = MsgRecCoAP[61];
        FalhaIv2 = MsgRecCoAP[62];
        SubTensaoInv2 = MsgRecCoAP[63];
        SobreTensaoInv2 = MsgRecCoAP[64];
        SobreTempDrInv2 = MsgRecCoAP[65];
        SobreTempTrInv2 = MsgRecCoAP[66];
        DjAbIv2 = MsgRecCoAP[67];

        CDBat = MsgRecCoAP[68];
        CxAzNvBx = MsgRecCoAP[69];
        EdCxAzCheia = MsgRecCoAP[70];
        FonteCC2Ligada = MsgRecCoAP[71];
        EstadoCxAz = MsgRecCoAP[72];
        FonteCC1Ligada = MsgRecCoAP[73];

        SobreCorrInv1 = MsgRecCoAP[74];
        SobreCorrInv2 = MsgRecCoAP[75];

        // Le o estado das saidas digitais
        int k = 112;
        byte[] SD = new byte[128];
        for (int i = 0; i < 32; i++){
            SD[i] = MsgRecCoAP[k];
            k = k + 1;
        }

        // Carrega as variaveis com os valores das saidas digitais do Concentrador Arduino Mega
        Iv1Lig = SD[1];
        CT2Inv = SD[17];
        CT1Inv = SD[0];
        CT3Inv = SD[2];
        Iv2Lig = SD[10];
        EstFonteCC = SD[16];

        // Le as Medidas da mensagem recebida do Concentrador Arduino Mega (medidas 0 a 47)
        k = 160;
        int[] Med = new int[256];
        for (byte i = 0; i < 48; i++){
            Med[i] = Util.DoisBytesInt(MsgRecCoAP[k], MsgRecCoAP[k + 1]);
            k = k + 2;
        }

        // Carrega as medidas lidas do Concentrador Arduino Mega nas variaveis
        VBat = Med[0];           // Tensão do Banco de Baterias
        VMBat = Med[16];         // Tensão Média Estendida do Banco de Baterias
        VRede = Med[5];          // Tensão da Rede
        Icarga3 = Med[14];       // Corrente Carga 3 (Geladeira)
        ICircCC = Med[3];        // Corrente Total dos Circuitos CC
        IFonteCC = Med[11];      // Corrente de Saída da Fonte CC

        TmpBmbLig = Med[17];     // Tempo da Bomba Ligada
        TmpCxAzNvBx = Med[46];   // Tempo da Caixa Azul em Nivel Baixo

        // Leitura e Cálculo das Medidas referentes à Geração e Consumo
        VP12 = Med[18];          // 0x3100 - PV array voltage 1
        IS12 = Med[19];          // 0x3101 - PV array current 1
        WS12 = Med[20];          // 0x3102 - PV array power 1
        VBat1 = Med[21];         // 0x3104 - Battery voltage 1
        ISCC1 = Med[22];         // 0x3105 - Battery charging current 1
        WSCC1 = Med[23];         // 0x3106 - Battery charging power 1
        TBat =  Med[24];         // 0x3110 - Battery Temperature 1

        VP34 = Med[26];          // 0x3100 - PV array voltage 2
        IS34 = Med[27];          // 0x3101 - PV array current 2
        WS34 = Med[28];          // 0x3102 - PV array power 2
        VBat2 = Med[29];         // 0x3104 - Battery voltage 2
        ISCC2 = Med[30];         // 0x3105 - Battery charging current 2
        WSCC2 = Med[31];         // 0x3106 - Battery charging power 2 (VG.Med[45])

        // Leitura e Cálculo das Medidas referentes ao Inversor 1
        IEIv1 = Med[12];         					// Corrente de Entrada do Inversor 1 (15)
        WEIv1 = (VBat * IEIv1) / 100;			// Potência de Entrada do Inversor 1 (VG.Med[41])
        VSIv1 = Med[4];          					// Tensão de Saída do Inversor 1
        ISInv1 = (7 * Med[10]) / 10;        			// Corrente de Saída do Inversor 1 (13)
        WSInv1 = (VSIv1 * ISInv1) / 1000;		// Potencia de Saida do Inversor 1 (VG.Med[42])
        TDInv1 = Med[8];         					// Temperatura do Driver do Inversor 1 (2)
        TTInv1 = Med[9];         					// Temperatura do Transformador do Inversor 1 (7)
        EfIv1 = 0;
        if (WEIv1 > 2000) {                          // Se o Inversor 1 está ligado,
            EfIv1 = (100*WSInv1)/WEIv1;		// calcula a Eficiência do Inversor 1
        }
        else {
            EfIv1 = 0;
        }
        SDIv1 = 0;

        // Leitura e Cálculo das Medidas referentes ao Inversor 2
        double IEInversor2 = 838 * Med[15];  //  838
        IEIv2 = (int)(IEInversor2 / 1000); 			 // Corrente de Entrada do Inversor 2 (12)
        WEIv2 = (VBat * IEIv2) / 100;         	 // Potencia de Entrada do Inversor 2 (VG.Med[38])
        VSIv2 = Med[6];          					 // Tensão de Saída do Inversor 2
        ISInv2 = Med[13];
        WSInv2 = (VSIv2 * ISInv2) / 1000;       	 // Potencia de Saida do Inversor 2 (VG.Med[39])
        TDInv2 = Med[2];         					 // Temperatura do Driver do Inversor 2 (8)
        TTInv2 = Med[7];         					 // Temperatura do Transformador do Inversor 2 (9)
        EfIv2 = 0;
        if (WEIv2 > 2000) {                           // Se o Inversor 2 está ligado,
            EfIv2 = (100*WSInv2) / WEIv2;		 // calcula a Eficiência do Inversor 2
        }
        else {
            EfIv2 = 0;
        }
        SDIv2 = 0;

        ITotGer = Med[33];       				// Corrente Total Gerada
        WCircCC = Med[35];       				// Potencia Consumida pelos Circuitos de 24Vcc
        WFonteCC = Med[36];      				// Potencia Fornecida pela Fonte 24Vcc
        IBat = Med[37];          				// Corrente de Carga ou Descarga do Banco de Baterias
        WBat = (VBat * IBat) / 100;				// Potência de Carga/Descarga do Banco de Baterias
        ITotGer = ISCC1 + ISCC2;				    // Corrente Total Gerada
        WTotGer = WSCC1 + WSCC2;				// Potência Total Gerada
        ITotCg = IEIv1 + IEIv2 + (ICircCC/10);	// Corrente Total Consumida pelas Cargas
        WTotCg =  WEIv1 + WEIv2 + WCircCC;		// Potência Total Consumida pelas Cargas

        // Cálculo da Saude do Controlador de Carga 1
        SDCC1 = 0;
        if (WSCC1 > 0) {
            SDCC1 = 100* (WS12 / WSCC1);
        }
        else {
            if (WS12 == 0) {
                SDCC1 = 100;
            }
            else {
                SDCC1 = 0;
            }
        }

        // Cálculo da Saude do Controlador de Carga 2
        SDCC2 = 0;
        if (WSCC2 > 0) {
            SDCC2 = 100 * (WS34 / WSCC2);
        }
        else {
            if (WS34 == 0) {
                SDCC2 = 100;
            }
            else {
                SDCC2 = 0;
            }
        }

        SDBat = 95;

        System.arraycopy(MsgRecCoAP, 0, MsgEnvSrv, 0, MsgRecCoAP.length);

        // As seguintes medidas são calculadas e carregadas no buffer para o servidor em nuvem
        MsgEnvSrv[226] = Util.ByteLow(ITotGer);   // Corrente Total Gerada
        MsgEnvSrv[227] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[228] = Util.ByteLow(ITotGer);   //  CB2Bytes(WTotGer, 34)  Potência Total Gerada
        MsgEnvSrv[229] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[248] = Util.ByteLow(ITotGer);   // CB2Bytes(ITotCg, 44)    Corrente Total Cargas
        MsgEnvSrv[249] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[250] = Util.ByteLow(ITotGer);   // CB2Bytes(WTotCg, 45);   Potência Total Cargas
        MsgEnvSrv[251] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[256] = Util.ByteLow(ITotGer);   // CB2Bytes(TemperaturaBoiler, 48);
        MsgEnvSrv[257] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[258] = Util.ByteLow(ITotGer);   // CB2Bytes(TemperaturaPlaca, 49);
        MsgEnvSrv[259] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[260] = Util.ByteLow(ITotGer);   // CB2Bytes(TempoBmbLigada, 50);
        MsgEnvSrv[261] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[262] = Util.ByteLow(ITotGer);   // CB2Bytes(TempoBmbDesligada, 51);
        MsgEnvSrv[263] = Util.ByteHigh(ITotGer);

        MsgEnvSrv[144] = (byte)EfIv1;  	// Eficiência do Inversor 1
        //InferenciaFuzzyInv1();        // Calcula a Saude do Inversor 1
        MsgEnvSrv[145] = (byte)SDIv1;  	// Carrega a Saude do Inversor 1 no Buffer
        //InferenciaFuzzyInv2();        // Calcula a Saude do Inversor 2
        MsgEnvSrv[146] = (byte)SDIv2;  	// Carrega a Saude do Inversor 2 no Buffer
        MsgEnvSrv[147] = (byte)EfIv2;  	// Eficiência do Inversor 2
        MsgEnvSrv[148] = (byte)SDCC1;  	// Saude do Controlador de Carga 1
        MsgEnvSrv[149] = (byte)SDCC2;  	// Saude do Controlador de Carga 2
        MsgEnvSrv[150] = (byte)SDBat;  	// Saude do Banco de Baterias

        return MsgEnvSrv;

    } // Fim da Rotina LeEstMedsPayload()


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
            Util.Terminal(MsgTerm, false, Verbose);
            int EstUTRAQ = 0;
            // Espera a Mensagem Binária de Resposta. Se a mensagem de resposta  for recebida, carrega nas variáveis
            try {
                DatagramPacket receivePacket = new DatagramPacket(MsgBinRec, TamMsgRsp);
                clientSocket.receive(receivePacket);

                LeEstMeds1(MsgBinRec);  // Carrega as informações recebidas nas variáveis
                MsgTerm = "Recebida Mensagem Binaria do Controlador de Água Quente";
                Util.Terminal(MsgTerm, false, Verbose);
                EstUTRAQ = 1;
            }
            catch(java.net.SocketTimeoutException e) {
                MsgBinRec[0] = 0x40;
                MsgBinRec[1] = 1;
                MsgBinRec[30] = 0;

                MsgTerm = "Erro: o Controlador de Água Quente nao Respondeu";
                Util.Terminal(MsgTerm, false, Verbose);
            }
            clientSocket.close();
        }
        catch (IOException err) {
            Util.Terminal("Erro na Rotina EnvRecMsgSrv: " + err, false, Verbose);
        }
        return(TamMsgRsp);
    }


    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: LeEstMeds1()                                                                                    *
    //                                                                                                                 *
    // Funcao: carrega as informações recebidas do Controlador de Água Quente nas variáveis                            *
    //                                                                                                                 *
    // Entrada: array com a mensagem recebida                                                                          *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    static void LeEstMeds1(byte[] MsgBinRec) {

        //byte EstComUTR = MsgBinRec[27];
        //byte EstCom1 = MsgBinRec[30];

        // Le os Estados Digitais da mensagem recebida
        int EstadoBombaAQ = BytetoInt(MsgBinRec[73]);		// Estado da Bomba de Água Quente
        int EstadoAquecedor = BytetoInt(MsgBinRec[72]);	    // Estado do Aquecedor do Boiler

        // Le as Medidas da mensagem recebida
        int TemperaturaBoiler = TwoBytetoInt(MsgBinRec[48], MsgBinRec[49]); 	// Temperatura do Boiler
        int TemperaturaPlaca = TwoBytetoInt(MsgBinRec[51], MsgBinRec[52]); 	    // Temperatura da Placa Solar
        int TempoBmbLigada = TwoBytetoInt(MsgBinRec[66], MsgBinRec[67]); 	    // Tempo da Bomba Ligada
        int TempoBmbDesligada = TwoBytetoInt(MsgBinRec[69], MsgBinRec[70]); 	// Tempo da Bomba Desligada

    }


    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: UBytetoInt                                                                                      *
    //                                                                                                                 *
    // Funcao: converte um byte sem sinal para inteiro (0 a 255)                                                       *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    private int UBytetoInt(byte valor) {
        int res = valor;
        if (valor < 0) {
            res = 256 + valor;
        }
        return res;
    }

    //*****************************************************************************************************************
    // Nome do Método: BytetoInt                                                                                      *
    //                                                                                                                *
    // Funcao: converte um valor byte para inteiro (conversao sem sinal)                                              *
    // Entrada: valor byte sem sinal de 0 a 255                                                                       *
    // Saida: valor (inteiro) sempre positivo de 0 a 255                                                              *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    static int BytetoInt(byte valor) {
        if (valor < 0) {
            return(256 + valor);
        }
        else {
            return(valor);
        }
    }

    //*****************************************************************************************************************
    // Nome do Método: TwoBytetoInt                                                                                   *
    //                                                                                                                *
    // Funcao: converte dois bytes em sequencia de um array para inteiro (conversao sem sinal)                        *
    // Entrada: dois bytes consecutivos (LSB e MSB) sem sinal de 0 a 255                                              *
    // Saida: valor (inteiro) sempre positivo de 0 a 65535                                                            *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    static int TwoBytetoInt(byte LSByte, byte MSByte) {
        int lsb;
        int msb;
        if (LSByte < 0) { lsb = LSByte + 256; }
        else { lsb = LSByte; }
        if (MSByte < 0) { msb = MSByte + 256; }
        else { msb = MSByte; }
        return (lsb + 256*msb);
    }

        /*
    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome da Rotina: InferenciaFuzzyInv1()                                                                           *
    //                                                                                                                 *
    // Funcao: calcula as variáveis de saída dos procedimentos de Inferência Fuzzy do Inversor 1                       *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    static void InferenciaFuzzyInv1() {
        double Ve = VBat/100;
        double We = WEIv1/100;
        double Vs = VSIv1/100;
        double Ws = WSInv1/100;
        double Ei = EfIv1;
        double Td = TDInv1/100;
        double Tt = TTInv1/100;

        // Entrar com o valor das entradas
        fis.setVariable("Ve", Ve);
        fis.setVariable("Vs", Vs);
        fis.setVariable("We", We);
        fis.setVariable("Ws", Ws);
        fis.setVariable("Ei", Ei);
        fis.setVariable("Td", Td);
        fis.setVariable("Tt", Tt);

        // Executar o arquivo FCL
        fis.evaluate();
        String StrEstresse = "" + fis.getVariable("EstresseInversor");
        String StrSaude = "" + fis.getVariable("SaudeInversor");
        String Ponto = ".";

        int Indice = StrEstresse.indexOf("value") + 7;
        EstrIv1 = Util.CharToByte(StrEstresse.charAt(Indice));
        Indice = Indice + 1;
        if (StrEstresse.charAt(Indice) == Ponto.charAt(0)) {
            Indice = Indice + 1;
        }
        else {
            EstrIv1 = 10*EstrIv1 + Util.CharToByte(StrEstresse.charAt(Indice));
            Indice = Indice + 1;
        }

        Indice = StrSaude.indexOf("value") + 7;
        SDIv1 = Util.CharToByte(StrSaude.charAt(Indice));
        Indice = Indice + 1;
        if (StrSaude.charAt(Indice) == Ponto.charAt(0)) {
            Indice = Indice + 1;
        }
        else {
            SDIv1 = 10 * SDIv1 + Util.CharToByte(StrEstresse.charAt(Indice));
            Indice = Indice + 1;
        }

    } // Fim da Rotina InferenciaFuzzyInv1


    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: InferenciaFuzzyInv2()                                                                           *
    //                                                                                                                 *
    // Funcao: calcula as variáveis de saída dos procedimentos de Inferência Fuzzy do Inversor 2                       *
    //                                                                                                                 *
    // Entrada: nao tem                                                                                                *
    //                                                                                                                 *
    // Saida: nao tem                                                                                                  *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    static void InferenciaFuzzyInv2() {
        double Ve = Bat/100;
        double We = WEIv2/100;
        double Vs = VSIv2/100;
        double Ws = WSInv2/100;
        double Ei = EfIv2;
        double Td = TDInv2/100;
        double Tt = TTInv2/100;

        // Entrar com o valor das entradas
        fis.setVariable("Ve", Ve);
        fis.setVariable("Vs", Vs);
        fis.setVariable("We", We);
        fis.setVariable("Ws", Ws);
        fis.setVariable("Ei", Ei);
        fis.setVariable("Td", Td);
        fis.setVariable("Tt", Tt);

        // Executar o arquivo FCL
        fis.evaluate();
        String StrSaude = "" + fis.getVariable("SaudeInversor");
        String Ponto = ".";

        //System.out.println(StrSaude);
        int Indice = StrSaude.indexOf("value") + 7;
        int SaudeInv2 = Util.CharToByte(StrSaude.charAt(Indice));
        Indice = Indice + 1;
        if (StrSaude.charAt(Indice) == Ponto.charAt(0)) {
            SDIv2 = SaudeInv2;
        }
        else {
            SDIv2 = 10*SaudeInv2 + Util.CharToByte(StrSaude.charAt(Indice));
        }
    } // Fim da Rotina
    */

}
