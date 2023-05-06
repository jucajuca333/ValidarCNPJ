import java.util.Scanner;
import javax.swing.text.MaskFormatter;

public class ValidaCnpj {
    public static void main(String[] args){
        // instância do scanner
        Scanner scanner = new Scanner(System.in);


        System.out.print("Insira seu CNPJ: ");
        String cnpj = scanner.nextLine();

        if(validarCnpj(cnpj)) {
            System.out.print("O CNPJ: " + cnpj + " é válido");
        } else {
            System.out.print("O CNPJ: " + cnpj + " é inválido");
        }

        // fechamento do scanner
        scanner.close();
    }

    public static boolean validarCnpj(String cnpj) {
        // remove todos os caracteres não numericos da string cnpj
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // verifica se o cpnj tem 14 digitos
        if(cnpj.length() != 14) {
            return false;
        }

        int [] cnpjDigitos = new int [14];
        // for para passar os valores da string para a array de inteiros
        for (int i = 0; i < cnpj.length(); i++) {
            //pega cada caractere na posicao i
            char digString = cnpj.charAt(i);
            // converte cada um deles em um valor inteiro
            int digito = Character.getNumericValue(digString);
            // atribuicao dos valores na array
            cnpjDigitos[i] = digito;
        }

        // peso para calcular os digitos verificadores
        int [] pesoDig1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int [] pesoDig2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // total para as somas
        int totalDig1 = 0;
        int totalDig2 = 0;

        // resto para calcular os digitos
        double restoDig1 = 0;
        double restoDig2 = 0;

        // digitos verificadores
        double digitoVerif1 = 0;
        double digitoVerif2 = 0;

        // for para calcular o primeiro digito
        for (int i = 0; i < pesoDig1.length; i++) {
            totalDig1 = totalDig1 + cnpjDigitos[i] * pesoDig1[i];
        }

        // atribuir valor do resto da divisão
        restoDig1 = totalDig1 % 11;

        // verificar primeiro digito
        if (restoDig1 < 2) {
            digitoVerif1 = 0;
        } else {
            digitoVerif1 =  11 - restoDig1;
        }

        // for para calcular o segundo digito
        for (int i = 0; i < pesoDig2.length; i++) {
            totalDig2 = totalDig2 + cnpjDigitos[i] * pesoDig2[i];
        }

        // atribuir o resto da divisão
        restoDig2 = totalDig2 % 11;

        // verificar segundo digito
        if (restoDig2 < 2) {
            digitoVerif2 = 0;
        } else {
            digitoVerif2 =  11 - restoDig2;
        }

        // verificar se os digitos calculados são iguais aos fonecidos
        if (digitoVerif1 == cnpjDigitos[12] && digitoVerif2 == cnpjDigitos[13]){
            return true;
        } else {
            return false;
        }
    }
}
