package boilerplate.common;

public class IncorrectAPIUsageException extends RuntimeException
{
	static String message = "A mod is using SteamAPI incorrectly :(";

	private static final long serialVersionUID = 1L;

    public IncorrectAPIUsageException() {
        super(message);
    }
}
