// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.svg;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/REC-SVG11-20110816/text.html#AlignmentBaselineProperty
 */
public class CssAlignmentBaseline extends org.w3c.css.properties.css.CssAlignmentBaseline {

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"auto", "baseline", "before-edge", "text-before-edge",
				"middle", "central", "after-edge", "text-after-edge", "ideographic",
				"alphabetic", "hanging", "mathematical"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}


	/**
	 * Create a new CssAlignmentBaseline
	 */
	public CssAlignmentBaseline() {
		value = initial;
	}

	/**
	 * Creates a new CssAlignmentBaseline
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssAlignmentBaseline(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;

		val = expression.getValue();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent id = (CssIdent) val;
			if (inherit.equals(id)) {
				value = inherit;
			} else {
				value = getAllowedIdent(id);
				if (value == null) {
					// we also check if CSS version is CSS3 and onward.
					if (ac.getCssVersion().compareTo(CssVersion.CSS3) >= 0) {
						value = org.w3c.css.properties.css3.CssAlignmentBaseline.getAllowedIdent(id);
					}
				}
				if (value == null) {
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				}
			}
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();
	}

	public CssAlignmentBaseline(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

