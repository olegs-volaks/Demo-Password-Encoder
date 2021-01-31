class IncorrectPasswordFormException extends RuntimeException {
    public IncorrectPasswordFormException() {
        super("Incorrect form of encoded password");
    }
}
