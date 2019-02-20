/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.cn.test.fixture;

import org.apache.fineract.cn.test.env.TestEnvironment;
import org.apache.fineract.cn.lang.AutoTenantContext;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class TenantDataStoreTestContext implements AutoCloseable {
  private final AutoTenantContext autoTenantContext;

  private TenantDataStoreTestContext(final String tenantName, final DataStoreTenantInitializer[] dataStoreTenantInitializers) {
    this.autoTenantContext = new AutoTenantContext(tenantName);
    for (final DataStoreTenantInitializer dataStoreTenantInitializer : dataStoreTenantInitializers)
    {
      dataStoreTenantInitializer.initializeTenant(tenantName);
    }
  }

  public static TenantDataStoreTestContext forDefinedTenantName(final String tenantName, final DataStoreTenantInitializer... dataStoreTenantInitializers)
  {
    return new TenantDataStoreTestContext(tenantName, dataStoreTenantInitializers);
  }

  public static TenantDataStoreTestContext forRandomTenantName(final DataStoreTenantInitializer... dataStoreTenantInitializers)
  {
    return new TenantDataStoreTestContext(TestEnvironment.getRandomTenantName(), dataStoreTenantInitializers);
  }

  @Override
  public void close()  {
    autoTenantContext.close();
  }
}
