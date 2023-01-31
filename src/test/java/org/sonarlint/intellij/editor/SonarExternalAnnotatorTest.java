/*
 * SonarLint for IntelliJ IDEA
 * Copyright (C) 2015-2023 SonarSource
 * sonarlint@sonarsource.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonarlint.intellij.editor;

import com.intellij.codeInsight.daemon.impl.AnnotationHolderImpl;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.sonarlint.intellij.AbstractSonarLintLightTests;
import org.sonarlint.intellij.SonarLintTestUtils;
import org.sonarlint.intellij.config.SonarLintTextAttributes;
import org.sonarlint.intellij.finding.persistence.FindingsManager;
import org.sonarlint.intellij.finding.issue.LiveIssue;
import org.sonarsource.sonarlint.core.commons.IssueSeverity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SonarExternalAnnotatorTest extends AbstractSonarLintLightTests {

  @Test
  public void testSeverityMapping() {
    assertThat(SonarExternalAnnotator.getTextAttrsKey(null)).isEqualTo(SonarLintTextAttributes.MAJOR);
    assertThat(SonarExternalAnnotator.getTextAttrsKey(IssueSeverity.MAJOR)).isEqualTo(SonarLintTextAttributes.MAJOR);
    assertThat(SonarExternalAnnotator.getTextAttrsKey(IssueSeverity.MINOR)).isEqualTo(SonarLintTextAttributes.MINOR);
    assertThat(SonarExternalAnnotator.getTextAttrsKey(IssueSeverity.BLOCKER)).isEqualTo(SonarLintTextAttributes.BLOCKER);
    assertThat(SonarExternalAnnotator.getTextAttrsKey(IssueSeverity.CRITICAL)).isEqualTo(SonarLintTextAttributes.CRITICAL);
    assertThat(SonarExternalAnnotator.getTextAttrsKey(IssueSeverity.INFO)).isEqualTo(SonarLintTextAttributes.INFO);
  }
}
