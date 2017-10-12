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

class NotifyingRunnable<R extends Runnable, A> extends DelegatingRunnable implements Runnable {

    private final TaskNotifier<? super R, ? super A> notifier;
    private final A attachment;

    NotifyingRunnable(final R delegate, final TaskNotifier<? super R, ? super A> notifier, final A attachment) {
        super(delegate);
        this.notifier = notifier;
        this.attachment = attachment;
    }

    @SuppressWarnings({ "unchecked" })
    protected R getDelegate() {
        return (R) super.getDelegate();
    }

    public void run() {
        JBossExecutors.run(getDelegate(), notifier, attachment);
    }
}
