/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions Copyright [year] [name of copyright owner]".
 *
 * Copyright 2009 Sun Microsystems, Inc.
 * Portions copyright 2014 ForgeRock AS.
 */
package org.forgerock.opendj.ldap.schema;

import static org.forgerock.opendj.ldap.schema.SchemaConstants.SYNTAX_MATCHING_RULE_USE_OID;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Matching rule use syntax tests.
 */
@Test
public class MatchingRuleUseSyntaxTest extends AbstractSyntaxTestCase {
    /** {@inheritDoc} */
    @Override
    @DataProvider(name = "acceptableValues")
    public Object[][] createAcceptableValues() {
        return new Object[][] {
            {
                "( 2.5.13.10 NAME 'fullMatchingRule' "
                        + " DESC 'description of matching rule' OBSOLETE " + " APPLIES 2.5.4.3 "
                        + " X-name 'this is an extension' )", true },
            {
                "( 2.5.13.10 NAME 'missingClosingParenthesis' "
                        + " DESC 'description of matching rule' " + " SYNTAX 2.5.4.3 "
                        + " X-name ( 'this is an extension' ) ", false }, };
    }

    /** {@inheritDoc} */
    @Override
    protected Syntax getRule() {
        return Schema.getCoreSchema().getSyntax(SYNTAX_MATCHING_RULE_USE_OID);
    }
}
