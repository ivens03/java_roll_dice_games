package configuracoes;

public enum TamanhosDeTelas {
    RES_800X600(800, 600, "4:3"),
    RES_1024X768(1024, 768, "4:3"),
    RES_1280X720(1280, 720, "16:9"),
    RES_1600X900(1600, 900, "16:9"),
    RES_1920X1080(1920, 1080, "16:9"),
    RES_2560X1080(2560, 1080, "21:9"),
    RES_2560X1440(2560, 1440, "16:9"),
    RES_3440X1440(3440, 1440, "21:9"),
    RES_3840X2160(3840, 2160, "16:9"),
    RES_5120X1440(5120, 1440, "32:9");

    private final int largura;
    private final int altura;
    private final String proporcao;

    TamanhosDeTelas(int largura, int altura, String proporcao) {
        this.largura = largura;
        this.altura = altura;
        this.proporcao = proporcao;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return largura + "x" + altura + " (" + proporcao + ")";
    }
}
