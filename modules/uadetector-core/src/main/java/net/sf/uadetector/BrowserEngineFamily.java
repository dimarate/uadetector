package net.sf.uadetector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import net.sf.qualitycheck.Check;

/**
 * This enum represents the more commonly used browser engine families.
 * 
 * @author Dmitri Bedunkevits
 */
public enum BrowserEngineFamily {

	/**
	 * Representation of an unknown browser engine
	 *
	 * <p>
	 * <strong>Attention</strong>: This is not a known browser engine family, but only a placeholder.
	 */
	UNKNOWN("unknown", Pattern.compile("^$")),

	/**
	 * Blink
	 */
	BLINK("Blink", Pattern.compile("Blink")),

	/**
	 * WebKit
	 */
	WEBKIT("WebKit", Pattern.compile("WebKit")),

	/**
	 * Trident
	 */
	TRIDENT("Trident", Pattern.compile("Trident")),

	/**
	 * Presto
	 */
	PRESTO("Presto", Pattern.compile("Presto")),

	/**
	 * Gecko
	 */
	GECKO("Gecko", Pattern.compile("Gecko")),

	/**
	 * Edge
	 */
	EDGE("Edge", Pattern.compile("Edge")),

	/**
	 * Netfront
	 */
	NETFRONT("Netfront", Pattern.compile("Netfront")),

	/**
	 * Khtml
	 */
	KHTML("Khtml", Pattern.compile("Khtml"));


	/**
	 * This method try to find by the given family name a matching enum value. The family name must match against an
	 * browser engine entry in UAS data file.
	 *
	 * @param family
	 *            name of an browser engine family
	 * @return the matching enum value or {@code BrowserEngineFamily#UNKNOWN}
	 * @throws net.sf.qualitycheck.exception.IllegalNullArgumentException
	 *             if the given argument is {@code null}
	 */
	@Nonnull
	public static BrowserEngineFamily evaluate(@Nonnull final String family) {
		Check.notNull(family, "family");

		BrowserEngineFamily result = UNKNOWN;

		// search by name
		result = evaluateByName(family);

		// search by pattern
		if (result == UNKNOWN) {
			result = evaluateByPattern(family);
		}

		return result;
	}

	/**
	 * This method try to find by the given family name a matching enum value. The family name will be evaluated against
	 * the stored name of an browser engine entry.
	 *
	 * @param family
	 *            name of an browser engine family
	 * @return the matching enum value or {@code BrowserEngineFamily#UNKNOWN}
	 * @throws net.sf.qualitycheck.exception.IllegalNullArgumentException
	 *             if the given argument is {@code null}
	 */
	@Nonnull
	protected static BrowserEngineFamily evaluateByName(@Nonnull final String family) {
		Check.notNull(family, "family");

		BrowserEngineFamily result = UNKNOWN;
		for (final BrowserEngineFamily value : values()) {
			if (value.getName().equalsIgnoreCase(family)) {
				result = value;
				break;
			}
		}

		return result;
	}

	/**
	 * This method try to find by the given family name a matching enum value. The family name will be evaluated against
	 * the stored regular expression of an browser engine entry.
	 *
	 * @param family
	 *            name of an browser engine family
	 * @return the matching enum value or {@code BrowserEngineFamily#UNKNOWN}
	 * @throws net.sf.qualitycheck.exception.IllegalNullArgumentException
	 *             if the given argument is {@code null}
	 */
	@Nonnull
	protected static BrowserEngineFamily evaluateByPattern(@Nonnull final String family) {
		Check.notNull(family, "family");

		BrowserEngineFamily result = UNKNOWN;
		for (final BrowserEngineFamily value : values()) {
			final Matcher m = value.getPattern().matcher(family);
			if (m.matches()) {
				result = value;
				break;
			}
		}

		return result;
	}

	/**
	 * The internal family name in the UAS database.
	 */
	@Nonnull
	private final String name;

	/**
	 * The regular expression which a family name must be match.
	 */
	@Nonnull
	private final Pattern pattern;

	BrowserEngineFamily(@Nonnull final String name, @Nonnull final Pattern pattern) {
		this.name = name;
		this.pattern = pattern;
	}

	/**
	 * Gets the internal family name in the UAS database.
	 * 
	 * @return the internal family name
	 */
	@Nonnull
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the regular expression which a family name must be match with.
	 * 
	 * @return regular expression
	 */
	@Nonnull
	public Pattern getPattern() {
		return pattern;
	}

}
