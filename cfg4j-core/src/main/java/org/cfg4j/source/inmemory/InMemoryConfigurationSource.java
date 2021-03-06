/*
 * Copyright 2015 Norbert Potocki (norbert.potocki@nort.pl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cfg4j.source.inmemory;

import static java.util.Objects.requireNonNull;

import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;

import java.util.Properties;

/**
 * Simple in-memory {@link ConfigurationSource}. A reference to the properties object is being kept allowing this source to sync up
 * on {@link #reload()} call.
 */
public class InMemoryConfigurationSource implements ConfigurationSource {

  private Properties properties;
  private final Properties sourceProperties;

  /**
   * Create in-memory configuration source with given {@code properties}. A reference to the properties object is being kept allowing
   * this source to sync up on {@link #reload()} call.
   *
   * @param properties properties to seed source.
   */
  public InMemoryConfigurationSource(Properties properties) {
    this.sourceProperties = requireNonNull(properties);
    this.properties = (Properties) properties.clone();
  }

  @Override
  public Properties getConfiguration(Environment environment) {
    return (Properties) properties.clone();
  }

  @Override
  public void init() {
    // NOP
  }

  @Override
  public void reload() {
    properties = (Properties) sourceProperties.clone();
  }

  @Override
  public String toString() {
    return "InMemoryConfigurationSource{" +
        "properties=" + properties +
        '}';
  }
}
