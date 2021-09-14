package Package01;
import java.io.*;
import java.net.*;
import java.lang.String;
import java.nio.charset.StandardCharsets;


public class Dados001 {

    // Atributos de Data e Hora
    private static byte Hora;
    private static byte Minuto;
    private static byte Segundo;
    private static byte Dia;
    private static byte Mes;
    private static byte Ano;

    // Atributos de Estado de Comunicação
    private static boolean EstComUTR;
    private static boolean EstComCC1;
    private static boolean EstComCC2;
    private static boolean EstCom1;

    // Atributos de Estados Digitais
    private static boolean DJEINV1;
    private static boolean EstRede;
    private static boolean MdOp;
    private static boolean MdCom;
    private static boolean MdCtrl1;
    private static boolean MdCtrl;
    private static boolean Carga1;
    private static boolean Carga2;
    private static boolean Carga3;
    private static boolean Carga4;
    private static boolean HabCom;
    private static boolean EstadoCarga3;
    private static boolean EstadoInversor1;
    private static boolean EstadoInversor2;
    private static boolean CDBat;
    private static boolean FonteCC1Ligada;
    private static boolean FonteCC2Ligada;
    private static boolean EstadoBombaAQ;
    private static boolean EstadoAquecedor;

    // Atributos de Estado da Caixa Azul e da Bomba
    private static byte EstadoCxAz;
    private static boolean CircBoia;
    private static boolean BoiaCxAzul;
    private static boolean CxAzNvBx;
    private static boolean EdCxAzCheia;
    private static boolean CircBomba;
    private static boolean AlRedeBomba;
    private static boolean BombaLigada;

    // Atributos de Alarmes
    private static boolean FalhaIv1;
    private static boolean SubTensaoInv1;
    private static boolean SobreTensaoInv1;
    private static boolean SobreTempDrInv1;
    private static boolean SobreTempTrInv1;
    private static boolean DjAbIv1;
    private static boolean FalhaIv2;
    private static boolean SubTensaoInv2;
    private static boolean SobreTensaoInv2;
    private static boolean SobreTempDrInv2;
    private static boolean SobreTempTrInv2;
    private static boolean DjAbIv2;
    private static boolean SobreCorrInv1;
    private static boolean SobreCorrInv2;

    // Atributos das saídas digitais
    private static boolean Iv1Lig;
    private static boolean CT2Inv;
    private static boolean CT1Inv;
    private static boolean CT3Inv;
    private static boolean Iv2Lig;
    private static boolean EstFonteCC;

    // Atributos de Medidas
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

    // Medidas da UTR do Sistema de Água Quente
    private static int TemperaturaBoiler; 	 // Temperatura do Boiler
    private static int TemperaturaPlaca; 	 // Temperatura da Placa Solar
    private static int TempoBmbLigada; 	     // Tempo da Bomba Ligada


    //------------------------------------------------------------------------------------------------------------------
    //                 Atributos em formato String para Montagem da Mensagem em formato Json
    //------------------------------------------------------------------------------------------------------------------

    // Estados de Comunicação (5 Variáveis)
    private static String comcnv;
    private static String comcnc;
    private static String comutr;
    private static String comcc1;
    private static String comcc2;

    // Informação geral (16 Variáveis)
    private static String clk;
    private static String data;
    private static String cmdex;
    private static String mdop;
    private static String mdcom;
    private static String mdct1;
    private static String mdct234;
    private static String encg1;
    private static String encg2;
    private static String encg3;
    private static String icg3;
    private static String vbat;
    private static String vrede;
    private static String estvrd;
    private static String tbat;
    private static String sdbat;

    // Estados e Medidas da Caixa d'água e da Bomba (7 Variáveis)
    private static String estcxaz;
    private static String nivcxaz;
    private static String estbmb;
    private static String estdjb;
    private static String estdjrb;
    private static String enbmb;
    private static String tmpbl;

    // Geração Solar e Consumo (18 Variáveis)
    private static String vp12;
    private static String is12;
    private static String iscc1;
    private static String wscc1;
    private static String sdcc1;
    private static String vp34;
    private static String is34;
    private static String iscc2;
    private static String wscc2;
    private static String sdcc2;
    private static String itotger;
    private static String wtotger;
    private static String itotcg;
    private static String wtotcg;
    private static String estft1;
    private static String estft2;
    private static String icircc;
    private static String wcircc;

    // Inversor 2 (10 Variáveis)
    private static String estiv2;
    private static String ieiv2;
    private static String weiv2;
    private static String isiv2;
    private static String vsiv2;
    private static String wsiv2;
    private static String tdiv2;
    private static String ttiv2;
    private static String efiv2;
    private static String sdiv2;

    // Inversor 1 (10 Variáveis)
    private static String estiv1;
    private static String ieiv1;
    private static String weiv1;
    private static String isiv1;
    private static String vsiv1;
    private static String wsiv1;
    private static String tdiv1;
    private static String ttiv1;
    private static String efiv1;
    private static String sdiv1;


    //*****************************************************************************************************************
    // Nome da Rotina: LeEstMedsPayload()                                                                             *
    //                                                                                                                *
    // Funcao: lê as informações dos estados e medidas recebidas do Concentrador na mensagem em formato CoAP-OSA-CBM  *
    //                                                                                                                *
    // Medidas (64): bytes 160 a 288 - 2 bytes por medida                                                             *
    //                                                                                                                *
    // Entrada: array de bytes com a mensagem binária em protocolo CoAP recebida e o núm. de bytes da mensagem        *
    //                                                                                                                *
    // Saida: array de bytes com a mensagem binária a ser enviada para o Servidor HTTP                                *
    //                                                                                                                *
    //*****************************************************************************************************************
    //
    static byte[] LeEstMedsPayload(byte[] MsgRecCoAP) {

        int TamMsgSrv = MsgRecCoAP.length;
        byte[] MsgEnvSrv = new byte[TamMsgSrv];

        // Lê as Informações de Data e Hora
        Hora = MsgRecCoAP[21];
        Minuto = MsgRecCoAP[22];
        Segundo = MsgRecCoAP[23];
        Dia = MsgRecCoAP[24];
        Mes = MsgRecCoAP[25];
        Ano = MsgRecCoAP[26];

        // Lê os estados de comunicação
        EstComUTR = MsgRecCoAP[27] > 0;
        EstComCC1 = MsgRecCoAP[28] > 0;
        EstComCC2 = MsgRecCoAP[29] > 0;
        EstCom1 = MsgRecCoAP[30] > 0;

        // Le os Estados Digitais
        DJEINV1 = MsgRecCoAP[37] > 0;
        EstRede = MsgRecCoAP[42] > 0;
        MdOp = MsgRecCoAP[43] > 0;
        MdCom = MsgRecCoAP[44] > 0;
        MdCtrl1 = MsgRecCoAP[55] > 0;
        MdCtrl = MsgRecCoAP[45] > 0;
        Carga1 = MsgRecCoAP[46] > 0;
        Carga2 = MsgRecCoAP[47] > 0;
        Carga3 = MsgRecCoAP[48] > 0;
        Carga4 = MsgRecCoAP[49] > 0;
        HabCom = MsgRecCoAP[50] > 0;
        EstadoInversor1 = MsgRecCoAP[51] > 0;
        EstadoInversor2 = MsgRecCoAP[52] > 0;
        EstadoCarga3 = MsgRecCoAP[53] > 0;
        CDBat = MsgRecCoAP[68] > 0;
        FonteCC1Ligada = MsgRecCoAP[73] > 0;
        FonteCC2Ligada = MsgRecCoAP[71] > 0;

        // Lê os estados da Caixa Azul e da Bomba
        EstadoCxAz = MsgRecCoAP[72];
        CircBoia = MsgRecCoAP[38] > 0;
        BoiaCxAzul = MsgRecCoAP[39] > 0;
        CxAzNvBx = MsgRecCoAP[69] > 0;
        EdCxAzCheia = MsgRecCoAP[70] > 0;
        CircBomba = MsgRecCoAP[40] > 0;
        AlRedeBomba = MsgRecCoAP[41] > 0;
        BombaLigada = MsgRecCoAP[54] > 0;

        // Le os Alarmes
        FalhaIv1 = MsgRecCoAP[56] > 0;
        SubTensaoInv1 = MsgRecCoAP[57] > 0;
        SobreTensaoInv1 = MsgRecCoAP[58] > 0;
        SobreTempDrInv1 = MsgRecCoAP[59] > 0;
        SobreTempTrInv1 = MsgRecCoAP[60] > 0;
        DjAbIv1 = MsgRecCoAP[61] > 0;
        FalhaIv2 = MsgRecCoAP[62] > 0;
        SubTensaoInv2 = MsgRecCoAP[63] > 0;
        SobreTensaoInv2 = MsgRecCoAP[64] > 0;
        SobreTempDrInv2 = MsgRecCoAP[65] > 0;
        SobreTempTrInv2 = MsgRecCoAP[66] > 0;
        DjAbIv2 = MsgRecCoAP[67] > 0;
        SobreCorrInv1 = MsgRecCoAP[74] > 0;
        SobreCorrInv2 = MsgRecCoAP[75] > 0;

        // Carrega as variaveis com os valores das saidas digitais
        int k = 112;
        CT1Inv = MsgRecCoAP[k] > 0;
        Iv1Lig = MsgRecCoAP[k + 1] > 0;
        CT3Inv = MsgRecCoAP[k + 2] > 0;
        Iv2Lig = MsgRecCoAP[k + 10] > 0;
        EstFonteCC = MsgRecCoAP[k + 16] > 0;
        CT2Inv = MsgRecCoAP[k + 17] > 0;

        // Le as Medidas da mensagem recebida do Concentrador Arduino Mega (medidas 0 a 47)
        k = 160;
        int[] Med = new int[256];
        for (byte i = 0; i < 48; i++){
            Med[i] = SupService.DoisBytesInt(MsgRecCoAP[k], MsgRecCoAP[k + 1]);
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
        IEIv1 = Med[12];         				// Corrente de Entrada do Inversor 1 (15)
        WEIv1 = (VBat * IEIv1) / 100;			// Potência de Entrada do Inversor 1 (VG.Med[41])
        VSIv1 = Med[4];          				// Tensão de Saída do Inversor 1
        ISInv1 = (7 * Med[10]) / 10;        	// Corrente de Saída do Inversor 1 (13)
        WSInv1 = (VSIv1 * ISInv1) / 1000;		// Potencia de Saida do Inversor 1 (VG.Med[42])
        TDInv1 = Med[8];         				// Temperatura do Driver do Inversor 1 (2)
        TTInv1 = Med[9];         				// Temperatura do Transformador do Inversor 1 (7)
        int EfIv1 = SupService.CalcEficienciaInversor(WEIv1, WSInv1);
        int SDIv1 = 0;

        // Leitura e Cálculo das Medidas referentes ao Inversor 2
        double IEInversor2 = 838 * Med[15];     //  838
        IEIv2 = (int)(IEInversor2 / 1000); 		// Corrente de Entrada do Inversor 2 (12)
        WEIv2 = (VBat * IEIv2) / 100;         	// Potencia de Entrada do Inversor 2 (VG.Med[38])
        VSIv2 = Med[6];          				// Tensão de Saída do Inversor 2
        ISInv2 = Med[13];
        WSInv2 = (VSIv2 * ISInv2) / 1000;       // Potencia de Saida do Inversor 2 (VG.Med[39])
        TDInv2 = Med[2];         				// Temperatura do Driver do Inversor 2 (8)
        TTInv2 = Med[7];         				// Temperatura do Transformador do Inversor 2 (9)
        int EfIv2 = SupService.CalcEficienciaInversor(WEIv2, WSInv2);
        int SDIv2 = 0;

        ITotGer = Med[33];       				// Corrente Total Gerada
        WCircCC = Med[35];       				// Potencia Consumida pelos Circuitos de 24Vcc
        WFonteCC = Med[36];      				// Potencia Fornecida pela Fonte 24Vcc
        IBat = Med[37];          				// Corrente de Carga ou Descarga do Banco de Baterias
        WBat = (VBat * IBat) / 100;				// Potência de Carga/Descarga do Banco de Baterias
        ITotGer = ISCC1 + ISCC2;				// Corrente Total Gerada
        WTotGer = WSCC1 + WSCC2;				// Potência Total Gerada
        ITotCg = IEIv1 + IEIv2 + (ICircCC/10);	// Corrente Total Consumida pelas Cargas
        WTotCg =  WEIv1 + WEIv2 + WCircCC;		// Potência Total Consumida pelas Cargas

        SDCC1 = 0;
        SDCC2 = 0;
        SDBat = 0;

        System.arraycopy(MsgRecCoAP, 0, MsgEnvSrv, 0, MsgRecCoAP.length);

        // As seguintes medidas são calculadas e carregadas no buffer para o servidor em nuvem
        MsgEnvSrv[226] = SupService.ByteLow(ITotGer);   // Corrente Total Gerada
        MsgEnvSrv[227] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[228] = SupService.ByteLow(ITotGer);   //  CB2Bytes(WTotGer, 34)  Potência Total Gerada
        MsgEnvSrv[229] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[248] = SupService.ByteLow(ITotGer);   // CB2Bytes(ITotCg, 44)    Corrente Total Cargas
        MsgEnvSrv[249] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[250] = SupService.ByteLow(ITotGer);   // CB2Bytes(WTotCg, 45);   Potência Total Cargas
        MsgEnvSrv[251] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[256] = SupService.ByteLow(ITotGer);   // CB2Bytes(TemperaturaBoiler, 48);
        MsgEnvSrv[257] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[258] = SupService.ByteLow(ITotGer);   // CB2Bytes(TemperaturaPlaca, 49);
        MsgEnvSrv[259] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[260] = SupService.ByteLow(ITotGer);   // CB2Bytes(TempoBmbLigada, 50);
        MsgEnvSrv[261] = SupService.ByteHigh(ITotGer);

        MsgEnvSrv[262] = SupService.ByteLow(ITotGer);   // CB2Bytes(TempoBmbDesligada, 51);
        MsgEnvSrv[263] = SupService.ByteHigh(ITotGer);

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

    }

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: CarregaAtributosString                                                                          *
    //                                                                                                                 *
    // Funcao: carrega os valores das variáveis nos atributos em formato String                                        *
    //                                                                                                                 *
    // Entrada: não tem                                                                                                *
    //                                                                                                                 *
    // Saida: não tem                                                                                                  *
    //                                                                                                                 *
    //******************************************************************************************************************
    //
    public static void CarregaAtributosString() {

        // Estados de Comunicacao
        comcnc = SupService.EstadoSimples(EstCom1, "Normal", "Falha");
        comutr = SupService.EstadoSimples(EstComUTR, "Normal", "Falha");
        comcc1 = SupService.EstadoSimples(EstComCC1, "Normal", "Falha");
        comcc2 = SupService.EstadoSimples(EstComCC2, "Normal", "Falha");

        // Hora e Data da UTR
        clk = SupService.ImpHora(Hora, Minuto, Segundo);
        data = SupService.ImpData(Dia, Mes, Ano);

        // Estados Gerais
        mdop = SupService.EstadoSimples(MdOp, "Normal", "Economia");
        mdcom = SupService.EstadoSimples(MdCom, "Remoto", "Local");
        mdct1 = SupService.EstadoSimples(EstComCC2, "Automatico", "Manual");
        mdct234 = SupService.EstadoSimples(MdCtrl, "Automatico", "Manual");

        // Estado da Fonte de Energia das Cargas 1, 2, 3 e 4
        encg1 = SupService.EstFonteEnergia(CT2Inv, Carga1, "Inversor 2", "Rede", "Rede (Hab)");
        encg2 = SupService.EstFonteEnergia(CT1Inv, Carga2, "Inversor 2", "Rede", "Rede (Hab)");
        encg3 = SupService.EstFonteEnergia(CT3Inv, Carga3, "Inversor 2", "Rede", "Rede (Hab)");
        enbmb = SupService.EstFonteEnergia(Iv1Lig, Carga4, "Inversor 1", "Rede", "Rede (Hab)");

        // Medida da Corrente da Carga 3 (Geladeira)
        icg3 = SupService.FrmAna3(Icarga3);

        // Medidas do Banco de Baterias
        vbat = SupService.FrmAna(VBat);
        tbat = SupService.FrmAna(TBat);
        sdbat = SupService.FrmAnaInt(SDBat);

        // Medida e Estado da Tensão da Rede
        vrede = SupService.FrmAna(VRede);
        estvrd = SupService.EstadoRede(EstRede, VRede, 19000);

        // Estados e Medidas da Caixa d'Água e da Bomba
        estcxaz = SupService.EstadoCaixaAzul(EstadoCxAz);
        nivcxaz = SupService.NivelCaixaAzul(EstadoCxAz);
        estbmb = SupService.EstadoSimples(CircBomba, "Ligada", "Desligada");
        estdjb = SupService.EstadoSimples(CircBoia, "Ligado", "Desligado");
        estdjrb = SupService.EstadoDepRede(EstRede, AlRedeBomba, "Ligado", "Desligado");
        tmpbl = SupService.FormAnaHora(TmpBmbLig);

        // Medidas da Geração Solar e Consumo
        vp12 = SupService.FrmAna(VP12);
        is12 = SupService.FrmAna(IS12);
        iscc1 = SupService.FrmAna(ISCC1);
        wscc1 = SupService.FrmAna(WSCC1);
        sdcc1 = SupService.FrmAnaInt(SDCC1);

        vp34 = SupService.FrmAna(VP34);
        is34 = SupService.FrmAna(IS34);
        iscc2 = SupService.FrmAna(ISCC2);
        wscc2 = SupService.FrmAna(WSCC2);
        sdcc2 = SupService.FrmAnaInt(SDCC2);

        itotger = SupService.FrmAna(ITotGer);
        wtotger = SupService.FrmAna(WTotGer);
        itotcg = SupService.FrmAna(ITotCg);
        wtotcg = SupService.FrmAna(WTotCg);

        // Estado das Fontes CC1 e CC2
        estft1 = SupService.EstadoDepRede(EstRede, FonteCC1Ligada, "Ligada", "Desligada");
        estft2 = SupService.EstadoDepRede(EstRede, FonteCC2Ligada, "Ligada", "Desligada");

        // Medidas dos Circuitos de Corrente Continua
        icircc = SupService.FrmAna3(ICircCC);
        wcircc = SupService.FrmAna(WCircCC);

        // Estados e Medidas do Inversor 2
        estiv2 = SupService.EstadoSimples(Iv2Lig, "Ligado", "Desligado");
        ieiv2 = SupService.FrmAna(IEIv2);
        weiv2 = SupService.FrmAna(WEIv2);
        vsiv2 = SupService.FrmAna(VSIv2);
        isiv2 = SupService.FrmAna(ISInv2);
        wsiv2 = SupService.FrmAna(WSInv2);
        tdiv2 = SupService.FrmAna(TDInv2);
        ttiv2 = SupService.FrmAna(TTInv2);
        efiv2 = SupService.FrmAna(EfIv2);
        sdiv2 = SupService.FrmAna(SDIv2);

        // Estados e Medidas do Inversor 2
        estiv1 = SupService.EstadoSimples(Iv1Lig, "Ligado", "Desligado");
        ieiv1 = SupService.FrmAna(IEIv1);
        weiv1 = SupService.FrmAna(WEIv1);
        vsiv1 = SupService.FrmAna(VSIv1);
        isiv1 = SupService.FrmAna(ISInv1);
        wsiv1 = SupService.FrmAna(WSInv1);
        tdiv1 = SupService.FrmAna(TDInv1);
        ttiv1 = SupService.FrmAna(TTInv1);
        efiv1 = SupService.FrmAna(EfIv1);
        sdiv1 = SupService.FrmAna(SDIv1);

    }

    //******************************************************************************************************************
    //                                                                                                                 *
    // Nome do Método: MontaJson                                                                                       *
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

        CarregaAtributosString();

        int numObj = 66;
        String[][] KeyValue = new String[numObj][2];

        // Estados de Comunicação
        KeyValue[0] = SupService.EntKeyValue("COMCNV", "Normal");
        KeyValue[1] = SupService.EntKeyValue("COMCNC", comcnc);
        KeyValue[2] = SupService.EntKeyValue("COMUTR", comutr);
        KeyValue[3] = SupService.EntKeyValue("COMCC1", comcc1);
        KeyValue[4] = SupService.EntKeyValue("COMCC2", comcc2);

        // Estados e Medidas Gerais
        KeyValue[5] = SupService.EntKeyValue("CLK", clk);
        KeyValue[6] = SupService.EntKeyValue("DATA", data);
        KeyValue[7] = SupService.EntKeyValue("CMDEX", "Atualiza");
        KeyValue[8] = SupService.EntKeyValue("MDOP", mdop);
        KeyValue[9] = SupService.EntKeyValue("MDCOM", mdcom);
        KeyValue[10] = SupService.EntKeyValue("MDCT1", mdct1);
        KeyValue[11] = SupService.EntKeyValue("MDCT234", mdct234);
        KeyValue[12] = SupService.EntKeyValue("ENCG1", encg1);
        KeyValue[13] = SupService.EntKeyValue("ENCG2", encg2);
        KeyValue[14] = SupService.EntKeyValue("ENCG3", encg3);
        KeyValue[15] = SupService.EntKeyValue("ICG3", icg3);
        KeyValue[16] = SupService.EntKeyValue("VBAT", vbat);
        KeyValue[17] = SupService.EntKeyValue("VREDE", vrede);
        KeyValue[18] = SupService.EntKeyValue("ESTVRD", estvrd);
        KeyValue[19] = SupService.EntKeyValue("TBAT", tbat);
        KeyValue[20] = SupService.EntKeyValue("SDBAT", sdbat);

        // Estados e Medidas da Caixa d'Água e Bomba (7 Variáveis)
        KeyValue[21] = SupService.EntKeyValue("ESTCXAZ", estcxaz);
        KeyValue[22] = SupService.EntKeyValue("NIVCXAZ", nivcxaz);
        KeyValue[23] = SupService.EntKeyValue("ESTBMB", estbmb);
        KeyValue[24] = SupService.EntKeyValue("ESTDJB", estdjb);
        KeyValue[25] = SupService.EntKeyValue("ESTDJRB", estdjrb);
        KeyValue[26] = SupService.EntKeyValue("ENBMB", enbmb);
        KeyValue[27] = SupService.EntKeyValue("TMPBL", tmpbl);

        // Geração Solar e Consumo (18 variáveis)
        KeyValue[28] = SupService.EntKeyValue("VP12", vp12);
        KeyValue[29] = SupService.EntKeyValue("IS12", is12);
        KeyValue[30] = SupService.EntKeyValue("ISCC1", iscc1);
        KeyValue[31] = SupService.EntKeyValue("WSCC1", wscc1);
        KeyValue[32] = SupService.EntKeyValue("SDCC1", sdcc1);
        KeyValue[33] = SupService.EntKeyValue("VP34", vp34);
        KeyValue[34] = SupService.EntKeyValue("IS34", is34);
        KeyValue[35] = SupService.EntKeyValue("ISCC2", iscc2);
        KeyValue[36] = SupService.EntKeyValue("WSCC2", wscc2);
        KeyValue[37] = SupService.EntKeyValue("SDCC2", sdcc2);
        KeyValue[38] = SupService.EntKeyValue("ITOTGER", itotger);
        KeyValue[39] = SupService.EntKeyValue("WTOTGER", wtotger);
        KeyValue[40] = SupService.EntKeyValue("ITOTCG", itotcg);
        KeyValue[41] = SupService.EntKeyValue("WTOTCG", wtotcg);
        KeyValue[42] = SupService.EntKeyValue("ESTFT1", estft1);
        KeyValue[43] = SupService.EntKeyValue("ESTFT2", estft2);
        KeyValue[44] = SupService.EntKeyValue("ICIRCC", icircc);
        KeyValue[45] = SupService.EntKeyValue("WCIRCC", wcircc);

        // Informação do Inversor 2 (10 Variáveis)
        KeyValue[46] = SupService.EntKeyValue("ESTIV2", estiv2);
        KeyValue[47] = SupService.EntKeyValue("IEIV2", ieiv2);
        KeyValue[48] = SupService.EntKeyValue("WEIV2", weiv2);
        KeyValue[49] = SupService.EntKeyValue("VSIV2", vsiv2);
        KeyValue[50] = SupService.EntKeyValue("ISIV2", isiv2);
        KeyValue[51] = SupService.EntKeyValue("WSIV2", wsiv2);
        KeyValue[52] = SupService.EntKeyValue("TDIV2", tdiv2);
        KeyValue[53] = SupService.EntKeyValue("TTIV2", ttiv2);
        KeyValue[54] = SupService.EntKeyValue("EFIV2", efiv2);
        KeyValue[55] = SupService.EntKeyValue("SDIV2", sdiv2);

        // Informação do Inversor 1 (10 Variáveis)
        KeyValue[56] = SupService.EntKeyValue("ESTIV1", estiv1);
        KeyValue[57] = SupService.EntKeyValue("IEIV1", ieiv1);
        KeyValue[58] = SupService.EntKeyValue("WEIV1", weiv1);
        KeyValue[59] = SupService.EntKeyValue("VSIV1", vsiv1);
        KeyValue[60] = SupService.EntKeyValue("ISIV1", isiv1);
        KeyValue[61] = SupService.EntKeyValue("WSIV1", wsiv1);
        KeyValue[62] = SupService.EntKeyValue("TDIV1", tdiv1);
        KeyValue[63] = SupService.EntKeyValue("TTIV1", ttiv1);
        KeyValue[64] = SupService.EntKeyValue("EFIV1", efiv1);
        KeyValue[65] = SupService.EntKeyValue("SDIV1", sdiv1);

        return (SupService.JsonString(KeyValue, numObj));
    }

}
