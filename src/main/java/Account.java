import java.util.Random;

public class Account {
    private String lastname;
    private String name;
    private String email;
    private String password;
    private String dob;

    public Account() {
        this.lastname = generateRandomAlphabetic(5);
        this.name = generateRandomAlphabetic( 7 );
        this.email = generateEmailPrefix()+"@testmail.com";
        this.password=generateRandomPassword();
        dob = "31/05/1992";
    }

    private String generateRandomAlphabetic(int limit){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = limit;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    private String generateEmailPrefix(){
        return this.lastname+'.'+this.name+new Random().nextInt(999);
    }

    private String generateRandomPassword(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 25;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }
}
