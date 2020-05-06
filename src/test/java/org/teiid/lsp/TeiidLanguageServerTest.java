/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.lsp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.teiid.lsp.completion.DDLGenericCompletionItem;

@DisplayName("Language Server Tests")
public class TeiidLanguageServerTest extends AbstractTeiidLanguageServerTest {
	
	@DisplayName("Testing completion for an empty file")
	@ParameterizedTest
	@ValueSource(strings = {".sql", ".ddl"})
	public void testProvideCompletionForEmptyFile(String usedExtension) throws Exception {
		TeiidLanguageServer languageServer = initializeLanguageServer("", usedExtension);
		
		CompletableFuture<Either<List<CompletionItem>, CompletionList>> completions = getCompletionFor(languageServer, new Position(0, 0));
		
		assertThat(completions.get().getLeft()).contains(new DDLGenericCompletionItem().getCreateDataView());
	}
	
	@DisplayName("Testing completion for a non-compatible file (neither .ddl nor .sql)")
	@Test
	public void testProvideNoCompletionForNonDDLFile() throws Exception {
		TeiidLanguageServer languageServer = initializeLanguageServer("", ".anotherextension");
		
		CompletableFuture<Either<List<CompletionItem>, CompletionList>> completions = getCompletionFor(languageServer, new Position(0, 0));
		
		assertThat(completions.get()).isNull();
	}
	
}

