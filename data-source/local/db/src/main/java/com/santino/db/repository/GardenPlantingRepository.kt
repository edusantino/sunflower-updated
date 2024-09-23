/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.santino.db.repository

import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings
import kotlinx.coroutines.flow.Flow

interface GardenPlantingRepository {
    suspend fun createGardenPlanting(plantId: String): Long
    suspend fun removeGardenPlanting(plantId: String)
    fun isPlanted(plantId: String): Flow<Boolean>
    fun getPlantedGardens(): Flow<List<PlantAndGardenPlantings>>
}