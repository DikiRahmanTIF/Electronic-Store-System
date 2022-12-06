import java.text.NumberFormat;
import java.util.Locale;

public class EleBackend {
    private String namaKustomer;
    private int total;
    private String jenisLayanan;
    private String kategoriPaket;
    private String tipePengantaran;
    private String Alamat;
    private int totalHarga;
    private int hargaJenisLayanan;
    private int hargaKategoriPaket;

    private NumberFormat nf;

    public EleBackend(){
        nf = NumberFormat.getInstance(Locale.US);
    }

    public int getHargaJenisLayanan() {
        return hargaJenisLayanan;
    }

    public String getHargaJenisLayananString(){
        return nf.format(hargaJenisLayanan);
    }

    public void setHargaJenisLayanan(int hargaJenisLayanan) {
        this.hargaJenisLayanan = hargaJenisLayanan;
        setHarga(this.hargaJenisLayanan);
    }

    public int getHargaKategoriPaket() {
        return hargaKategoriPaket;
    }

    public String getHargaKategoriPaketString(){
        return nf.format(hargaKategoriPaket);
    }

    public void setHargaKategoriPaket(int hargaKategoriPaket) {
        this.hargaKategoriPaket = hargaKategoriPaket;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setHarga(int harga) {
        this.totalHarga += harga;
    }

    public String getNamaKustomer() {
        return namaKustomer;
    }

    public void setNamaKustomer(String namaKustomer) {
        this.namaKustomer = namaKustomer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
        switch (jenisLayanan){
            case "PC":
                setHargaJenisLayanan(7000000);
                break;
            case "Laptop":
                setHargaJenisLayanan(5000000);
                break;
            case "MacBook":
                setHargaJenisLayanan(11000000);
                break;
            case "HandPhone":
                setHargaJenisLayanan(2000000);
                break;
        }
    }

    public String getKategoriPaket() {
        return kategoriPaket;
    }

    public void setKategoriPaket(String kategoriPaket) {
        this.kategoriPaket = kategoriPaket;
        switch (kategoriPaket){
            case "Reguler":
                setHargaKategoriPaket(2000);
                break;
            case "Kilat":
                setHargaKategoriPaket(5000);
                break;
        }
    }

    public String getTipePengantaran() {
        return tipePengantaran;
    }

    public void setTipePengantaran(String tipePengantaran) {
        this.tipePengantaran = tipePengantaran;
        switch (tipePengantaran) {
            case "Antar Kerumah":
                setHarga(5000);
                break;
            case "Jemput Langsung":
                setHarga(0);
                break;
        }
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String kodePromo) {
        this.Alamat = kodePromo;
    }

    public String calculateTotalAkhir(){
        int totalHargaFinal = this.totalHarga* getTotal()+getHargaKategoriPaket();
        restartTotalHarga();
        return "Rp."+nf.format(totalHargaFinal);
    }

    public void restartTotalHarga(){
        this.totalHarga = 0;
        setHargaJenisLayanan(0);
        setHargaKategoriPaket(0);
    }
}
