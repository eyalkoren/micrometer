/**
 * Copyright 2017 Pivotal Software, Inc.
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
package io.micrometer.spring.export.datadog;

import com.netflix.spectator.api.Clock;
import com.netflix.spectator.api.Registry;
import io.micrometer.core.instrument.datadog.DatadogConfig;
import io.micrometer.core.instrument.datadog.DatadogRegistry;
import io.micrometer.core.instrument.spectator.SpectatorMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class DatadogMetricsConfiguration {
    @Bean
    SpectatorMeterRegistry meterRegistry(Registry registry) {
        return new SpectatorMeterRegistry(registry);
    }

    @Bean
    DatadogConfig datadogConfig(Environment environment) {
        return environment::getProperty;
    }

    @Bean
    DatadogRegistry datadogRegistry(DatadogConfig atlasConfig) {
        DatadogRegistry registry = new DatadogRegistry(Clock.SYSTEM, atlasConfig);
        registry.start();
        return registry;
    }
}
