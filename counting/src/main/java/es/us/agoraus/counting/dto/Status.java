package es.us.agoraus.counting.dto;

public enum Status {

	SUCCESS(Code.gen(1), "OK."),
	SPECIAL_COD_FALLBACK(Code.gen(2), "Special codification fallback."),
	EMPTY_VOTES(Code.gen(3), "The poll has no votes.");
	
	private final int code;
	private final String message;

	Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	private static class Code {

		private static final int PREFIX = 195;

		public static int gen(int code) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			sb.append(code);
			return Integer.valueOf(sb.toString());
		}

	}
	
}
