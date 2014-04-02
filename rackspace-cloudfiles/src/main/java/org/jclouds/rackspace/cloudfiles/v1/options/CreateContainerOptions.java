/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.rackspace.cloudfiles.v1.options;

import static org.jclouds.openstack.swift.v1.reference.SwiftHeaders.CONTAINER_METADATA_PREFIX;
import static org.jclouds.openstack.swift.v1.reference.SwiftHeaders.CONTAINER_VERSIONS_LOCATION;

import java.util.Map;

import org.jclouds.openstack.swift.v1.binders.BindMetadataToHeaders;
import org.jclouds.openstack.swift.v1.domain.Container;
import org.jclouds.openstack.swift.v1.features.ContainerApi;

/**
 * Options for creating a {@link Container}. 
 * 
 * @see <a href=
 *      "http://docs.openstack.org/api/openstack-object-storage/1.0/content/create-container.html">
 *      Create Container API</a>
 * 
 * @see ContainerApi#createIfAbsent(String, CreateContainerOptions)
 */
public class CreateContainerOptions extends org.jclouds.openstack.swift.v1.options.CreateContainerOptions {
   public static final CreateContainerOptions NONE = new CreateContainerOptions();

   /** 
    * Sets the metadata on a container at creation. 
    */
   public CreateContainerOptions metadata(Map<String, String> metadata) {
      if (!metadata.isEmpty()) {
         this.headers.putAll(bindMetadataToHeaders.toHeaders(metadata));
      }
      return this;
   }

   /** 
    * Sets the container that will contain object versions.
    */
   public CreateContainerOptions versionsLocation(String containerName) {
      this.headers.put(CONTAINER_VERSIONS_LOCATION, containerName);
      return this;
   }
   
   /** 
    * Sets the public ACL on the container so that anybody can read it. 
    */
   public CreateContainerOptions anybodyRead() {
      throw new UnsupportedOperationException();
   }

   public static class Builder {

      /** 
       * @see CreateContainerOptions#metadata 
       */
      public static CreateContainerOptions metadata(Map<String, String> metadata) {
         CreateContainerOptions options = new CreateContainerOptions();
         return options.metadata(metadata);
      }

      /** 
       * @see CreateContainerOptions#versionsLocation 
       */
      public static CreateContainerOptions versionsLocation(String containerName) {
         CreateContainerOptions options = new CreateContainerOptions();
         return options.versionsLocation(containerName);
      }
      
      /** 
       * Sets the public ACL on the container so that anybody can read it. 
       */
      public static CreateContainerOptions anybodyRead() {
         return new CreateContainerOptions();
      }
   }

   private static final BindMetadataToHeaders bindMetadataToHeaders = new BindMetadataToHeaders(CONTAINER_METADATA_PREFIX);
}
