/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wix.unicorn.movie

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.presentation.base.extension.inflate
import com.wix.unicorn.presentation.base.imageloader.ImageLoadDelegate
import kotlinx.android.synthetic.main.row_movie.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(), KoinComponent {

    var collection: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {
        private val imageLoadDelegate: ImageLoadDelegate by inject()

        fun bind(movieView: Movie) {
            imageLoadDelegate.loadImage(itemView.movie_poster, movieView.poster)
        }
    }
}
