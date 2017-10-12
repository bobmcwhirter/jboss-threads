/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.threads;

class ThreadNameNotatingExecutor implements DirectExecutor {

    private final String notation;
    private final DirectExecutor delegate;

    ThreadNameNotatingExecutor(final String notation, final DirectExecutor delegate) {
        this.notation = notation;
        this.delegate = delegate;
    }

    public void execute(final Runnable command) {
        final Thread thr = Thread.currentThread();
        final String oldName;
        oldName = thr.getName();
        thr.setName(oldName + " (" + notation + ')');
        try {
            delegate.execute(command);
        } finally {
            thr.setName(oldName);
        }
    }

    public String toString() {
        return String.format("%s notation=\"%s\" -> %s", super.toString(), notation, delegate);
    }
}
