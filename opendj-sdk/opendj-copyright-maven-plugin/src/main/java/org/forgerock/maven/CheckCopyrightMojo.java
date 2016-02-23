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
 * Copyright 2015-2016 ForgeRock AS.
 */
package org.forgerock.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * This be used to check that if a modified file contains a line that appears to
 * be a comment and includes the word "copyright", then it should contain the
 * current year.
 */
@Mojo(name = "check-copyright", defaultPhase = LifecyclePhase.VALIDATE)
public class CheckCopyrightMojo extends CopyrightAbstractMojo {
    private static final String IGNORE_COPYRIGHT_ERRORS_PROPERTY = "ignoreCopyrightErrors";

    /**
     * The property that may be used to prevent copyright date problems from
     * failing the build.
     */
    @Parameter(required = true, property = IGNORE_COPYRIGHT_ERRORS_PROPERTY, defaultValue = "false")
    private boolean ignoreCopyrightErrors;

    @Parameter(required = true, property = "skipCopyrightCheck", defaultValue = "false")
    private boolean checkDisabled;

    /**
     * Uses maven-scm API to identify all modified files in the current
     * workspace. For all source files, check if the copyright is up to date.
     *
     * @throws MojoFailureException
     *             if any
     * @throws MojoExecutionException
     *             if any
     */
    @Override
    public void execute() throws MojoFailureException, MojoExecutionException {
        if (checkDisabled) {
            getLog().info("Copyright check is disabled");
            return;
        }

        checkCopyrights();
        if (!getIncorrectCopyrightFilePaths().isEmpty()) {
            getLog().warn("Potential copyright year updates needed for the following files:");
            for (String filename : getIncorrectCopyrightFilePaths()) {
                getLog().warn("     " + filename);
            }

            if (!ignoreCopyrightErrors) {
                getLog().warn("Fix copyright date problems before proceeding, "
                        + "or use '-D" + IGNORE_COPYRIGHT_ERRORS_PROPERTY + "=true' to ignore copyright errors.");
                getLog().warn("You can use update-copyrights maven profile "
                        + "(mvn validate -Pupdate-copyrights) to automatically update copyrights.");
                getLog().warn("Use '-D" + DIFF_REFERENCE_BRANCH_NAME_PROPERTY + "=branchname' to change the reference "
                        + "branch which is used against your active local branch to check committed changes.");
                throw new MojoExecutionException("Found files with potential copyright year updates needed");
            }
        } else {
            getLog().info("Copyrights are up to date");
        }
    }
}
