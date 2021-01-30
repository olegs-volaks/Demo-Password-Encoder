public class Main {

    //  Demo code runner
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String password = "Demo pass";
        for (int i = 0; i < 1000; i++) {
            String hashPassword = passwordEncoder.encode(password, 8);
            String h2p = passwordEncoder.encode(password, 5);
            System.out.println(passwordEncoder.matches(hashPassword, password));
            System.out.println("h2p = " + h2p);
            System.out.println("hashPassword = " + hashPassword);
            System.out.println(passwordEncoder.matches(h2p, password));
        }
    }
}
