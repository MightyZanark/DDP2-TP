package assignments.assignment1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class NotaGeneratorTest {
    @Test
    public void testGenerateNota1() {
        String solution = "ID    : BOCI-08203712389-79\n" +
                "Paket : reguler\n" +
                "Harga :\n" +
                "20 kg x 7000 = 140000\n" +
                "Tanggal Terima  : 01/02/2023\n" +
                "Tanggal Selesai : 04/02/2023";
        assertEquals(
            solution,
            NotaGenerator.generateNota(
                "BOCI-08203712389-79",
                "reguler",
                20,
                "01/02/2023",
                false
            )
        );

        String sol1 = "ID    : IKAN-08123298423-84\n" +
                "Paket : express\n" + 
                "Harga :\n" +
                "2 kg x 12000 = 24000\n" +
                "Tanggal Terima  : 01/02/2023\n" +
                "Tanggal Selesai : 02/02/2023";
        assertEquals(
            sol1,
            NotaGenerator.generateNota(
                "IKAN-08123298423-84", 
                "express", 
                1, 
                "01/02/2023",
                false
            )
        );
    }

    @Test
    public void testGenerateId1() {
        assertEquals("DEK-082212345678-75", NotaGenerator.generateId("Dek Depe", "082212345678"));
        assertEquals("ANYA-12345-63", NotaGenerator.generateId("Anya Forger", "12345"));
        assertEquals("A-01-09", NotaGenerator.generateId("A Person", "01"));
    }

}
