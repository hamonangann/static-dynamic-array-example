import java.util.Scanner;

public class StaticArray {
    static String[] contoh;

    // Jumlah elemen yang tidak null
    static int jumlahElemen = 0;

    // Kapasitas array
    static int kapasitas;

    /**
    * Fungsi untuk cek input apakah bisa diubah ke integer dengan mencoba parse
    * @param s input yang mau dicek
    * @return true kalau bisa diubah
    */
    public static boolean cekInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
    * Fungsi untuk cetak array
    */
    public static void cetakArray() {
        System.out.print("Isi array: ");
        for (int i=0; i<jumlahElemen; i++) {
            System.out.print(contoh[i] + ", ");
        }
        System.out.println();
    }

    /**
    * Fungsi untuk memasukkan string baru di sebelah kanan elemen-elemen sebelumnya
    * @param s string yang mau dimasukkan
    */
    public static void tambahDiAkhir(String s) {
        if (jumlahElemen >= kapasitas) {
            System.out.println("Array sudah penuh, tidak bisa ditambah");
            return;
        }

        // indeks yang masih null adalah pada jumlahElemen
        contoh[jumlahElemen] = s;
        jumlahElemen++;
    }

    /**
    * Fungsi untuk menyisipkan string baru di suatu indeks
    * @param index di mana stringnya mau dimasukkan
    * @param s string yang mau dimasukkan
    */
    public static void sisipkanDiIndeks(int index, String s) {
        if (jumlahElemen >= kapasitas) {
            System.out.println("Array sudah penuh, tidak bisa ditambah");
            return;
        }

        if (index < 0 || index > jumlahElemen) {
            System.out.println("Tidak bisa disisipkan di indeks itu");
            return;
        }

        for (int i=jumlahElemen-1; i >= index; i--) {
            // geser dulu semua elemen di kanan yang mau disisipkan ke kanan
            contoh[i+1] = contoh[i]; 
        }

        // sekarang, sudah bisa disisipkan tanpa ada elemen yang hilang
        contoh[index] = s;
        jumlahElemen++;
    }

    /**
    * Fungsi untuk menghapus string di suatu indeks
    * @param index di mana stringnya mau dihapus
    */
    public static void hapusDiIndeks(int index) {
        if (index < 0 || index >= jumlahElemen) {
            System.out.println("Tidak bisa dihapus di indeks itu");
            return;
        }

        for (int i=index; i<jumlahElemen-1; i++) {
            // Intinya begini, elemen pada indeks 'terhapus' karena diganti elemen di kanannya
            // Dilakukan terus sampai sebelum indeks terkanan
            contoh[i] = contoh[i+1];
        }

        // Ganti indeks terkanan dengan null
        contoh[jumlahElemen-1] = null;

        // Kurangi jumlah elemen yang tidak null
        jumlahElemen--; 
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat mencoba static array!");

        System.out.println("Array statis itu kapasitasnya tidak bisa ditambah");
        System.out.println("Jadi, masukkan kapasitas: ");

        String inputKapasitas = input.nextLine();
        while (cekInteger(inputKapasitas) == false) {
            System.out.println("Kapasitas tidak valid");
            inputKapasitas = input.nextLine();
        } 

        kapasitas = Integer.parseInt(inputKapasitas);
        contoh = new String[kapasitas];

        while (true) {
            System.out.println("Pilih menu (ketik 0-4)");
            System.out.println("0. Cek isi array");
            System.out.println("1. Tambah elemen baru di akhir");
            System.out.println("2. Sisipkan elemen baru di suatu indeks");
            System.out.println("3. Hapus elemen di suatu indeks");
            System.out.println("4. Keluar");

            String pilihan = input.nextLine();
            
            if (pilihan.equals("0")) {
                cetakArray();

            } else if (pilihan.equals("1")) {
                System.out.println("Masukkan elemen: ");
                tambahDiAkhir(input.nextLine());

            } else if (pilihan.equals("2") || pilihan.equals("3")) {
                System.out.println("Masukkan indeks: ");
                String inputIndeks = input.nextLine();

                while (cekInteger(inputIndeks) == false) {
                    System.out.println("Indeks tidak valid");
                    inputIndeks = input.nextLine();
                } 

                int indeks = Integer.parseInt(inputIndeks);

                if (pilihan.equals("2")) {
                    System.out.println("Masukkan elemen:");
                    sisipkanDiIndeks(indeks, input.nextLine());

                } else {
                    hapusDiIndeks(indeks);
                }

            } else if (pilihan.equals("4")) {
                System.out.println("Program berakhir, tekan enter untuk exit...");
                input.nextLine();
                break;
                
            } else {
                System.out.print("Pilihan tidak valid");
            }

            System.out.println();
        }


        input.close();
    }
}