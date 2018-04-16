package net.sf.uadetector.internal.data.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import net.sf.qualitycheck.Check;
import net.sf.uadetector.BrowserEngineFamily;
import net.sf.uadetector.UserAgentFamily;

@Immutable
public final class BrowserEngine implements Identifiable, Serializable {

	@NotThreadSafe
	public static final class Builder {

		private static final String EMPTY = "";

		@Nonnull
		private BrowserEngineFamily family = BrowserEngineFamily.UNKNOWN;

		@Nonnull
		private String familyName = EMPTY;

		private int id = Integer.MIN_VALUE;

		@Nonnull
		private String infoUrl = EMPTY;

		@Nonnull
		private SortedSet<BrowserEnginePattern> patterns = new TreeSet<BrowserEnginePattern>();

		public Builder() {
			// default constructor
		}

		/**
		 * Creates a new instance of a builder with the data of the passed builder.
		 *
		 * @param builder
		 *            builder containing the data to be copied
		 * @throws net.sf.qualitycheck.exception.IllegalNullArgumentException
		 *             if the given argument is {@code null}
		 */
		private Builder(@Nonnull final Builder builder) {
			Check.notNull(builder, "builder");

			id = builder.id;
			family = builder.family;
			familyName = builder.familyName;
			infoUrl = builder.infoUrl;
		}

		public Builder(@Nonnull final BrowserEngine browserEngine) {
			Check.notNull(browserEngine, "browserEngine");
			id = Check.notNegative(browserEngine.getId(), "browserEngine.getId()");
			family = Check.notNull(browserEngine.getFamily(), "browserEngine.getFamily()");
			familyName = Check.notNull(browserEngine.getFamilyName(), "browserEngine.getFamilyName()");
			infoUrl = Check.notNull(browserEngine.getInfoUrl(), "browserEngine.getInfoUrl()");
			patterns = new TreeSet<BrowserEnginePattern>(Check.notNull(browserEngine.getPatterns(), "browserEngine.getPatterns()"));
		}

		@Nonnull
		public BrowserEngine build() {
			return new BrowserEngine(id, family, familyName, infoUrl, patterns);
		}

		/**
		 * Creates a copy (with all its data) of the current builder.
		 *
		 * @return a new instance of the current builder, never {@code null}
		 */
		@Nonnull
		public Builder copy() {
			return new Builder(this);
		}

		public int getId() {
			return id;
		}

		public String getInfoUrl() {
			return infoUrl;
		}

		@Nonnull
		public BrowserEngineFamily getFamily() {
			return family;
		}

		@Nonnull
		public String getFamilyName() {
			return familyName;
		}

		public SortedSet<BrowserEnginePattern> getPatterns() {
			return patterns;
		}

		@Nonnull
		public Builder addPatterns(@Nonnull final Set<BrowserEnginePattern> patterns) {
			Check.notNull(patterns, "patterns");

			this.patterns.addAll(patterns);
			return this;
		}

		@Nonnull
		public Builder setId(@Nonnegative final int id) {
			this.id = Check.notNegative(id, "id");
			return this;
		}

		@Nonnull
		public Builder setId(@Nonnull final String id) {
			setId(Integer.parseInt(Check.notEmpty(id, "id")));
			return this;
		}

		@Nonnull
		public Builder setInfoUrl(@Nonnull final String infoUrl) {
			this.infoUrl = Check.notNull(infoUrl, "infoUrl");
			return this;
		}

		@Nonnull
		private Builder setFamily(@Nonnull final BrowserEngineFamily family) {
			this.family = Check.notNull(family, "family");
			return this;
		}

		@Nonnull
		public Builder setFamilyName(@Nonnull final String familyName) {
			this.familyName = Check.notNull(familyName, "familyName");
			return setFamily(BrowserEngineFamily.evaluate(familyName));
		}

		@Nonnull
		public Builder setPatterns(@Nonnull final SortedSet<BrowserEnginePattern> patterns) {
			this.patterns = new TreeSet<BrowserEnginePattern>(Check.notNull(patterns, "patterns"));
			return this;
		}

	}

	private static final long serialVersionUID = 1L;

	private static int buildHashCode(@Nonnegative final int id, @Nonnull final BrowserEngineFamily family, @Nonnull final String familyName,
			@Nonnull final String infoUrl, @Nonnull final SortedSet<BrowserEnginePattern> patterns) {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + family.hashCode();
		result = prime * result + familyName.hashCode();
		result = prime * result + infoUrl.hashCode();
		result = prime * result + patterns.hashCode();
		return result;
	}

	@Nonnull
	private final BrowserEngineFamily family;

	@Nonnull
	private final String familyName;

	private final int hash;

	@Nonnegative
	private final int id;

	@Nonnull
	private final String infoUrl;

	@Nonnull
	private final SortedSet<BrowserEnginePattern> patterns;

	public BrowserEngine(@Nonnegative final int id, @Nonnull final BrowserEngineFamily family, @Nonnull final String familyName,
                         @Nonnull final String infoUrl, @Nonnull final SortedSet<BrowserEnginePattern> patterns) {
		this.id = Check.notNegative(id, "id");
		this.family = Check.notNull(family, "family");
		this.familyName = Check.notNull(familyName, "familyName");
		this.infoUrl = Check.notNull(infoUrl, "infoUrl");
		this.patterns = Collections.unmodifiableSortedSet(new TreeSet<BrowserEnginePattern>(Check.notNull(patterns, "patterns")));
		hash = buildHashCode(id, family, familyName, infoUrl, patterns);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BrowserEngine other = (BrowserEngine) obj;
		if (id != other.id) {
			return false;
		}
		if (!family.equals(other.family)) {
			return false;
		}
		if (!familyName.equals(other.familyName)) {
			return false;
		}
		if (!infoUrl.equals(other.infoUrl)) {
			return false;
		}
		if (!patterns.equals(other.patterns)) {
			return false;
		}
		return true;
	}

	@Override
	@Nonnegative
	public int getId() {
		return id;
	}

	@Nonnull
	public String getInfoUrl() {
		return infoUrl;
	}

	@Nonnull
	public BrowserEngineFamily getFamily() {
		return family;
	}

	@Nonnull
	public String getFamilyName() {
		return familyName;
	}

	@Nonnull
	public SortedSet<BrowserEnginePattern> getPatterns() {
		return patterns;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public String toString() {
		return "BrowserEngine [id=" + id + ", family=" + family + ", familyName=" + familyName + ", infoUrl=" + infoUrl + ", patterns=" + patterns + "]";
	}

}
