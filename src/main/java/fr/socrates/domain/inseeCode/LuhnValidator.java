package fr.socrates.domain.inseeCode;

import java.util.function.Predicate;

/**
 * On multiplie les chiffres de rang impair à partir de la droite par 1,
 * ceux de rang pair par 2 ; la somme des chiffres obtenus est un multiple de 10.
 *
 * Exemple :
 * pour vérifier :   7 3 2 8 2 9 3 2 0 0 0 0 7 4
 * rang pair x 2 :  14   4   4   6   0   0  14
 * rang impair x 1 :   3   8   9   2   0   0   4
 * ---------------------------------------------
 * somme : 1+4+3+4+8+4+9+6+2+0+0+0+0+1+4+4=50
 */
class LuhnValidator implements Predicate<String> {
    public boolean test(String code) {
        int total = 0;
        int digit;

        String reverse = new StringBuffer(code).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            digit = reverse.charAt(i) - 48;
            if ((i % 2) == 1) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }
        return (total % 10) == 0;
    }
}
