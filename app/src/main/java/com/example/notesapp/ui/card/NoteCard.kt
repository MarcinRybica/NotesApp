package com.example.notesapp.ui.card

import android.R.attr.background
import android.R.id.background
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.model.NoteItem


@Composable
fun NoteCard(note: NoteItem, onClick: () -> Unit){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(vertical = 8.dp)
            .border(
                3.dp,
                Color(0xFFFF7700),
                RoundedCornerShape(16.dp)
            )
            .background(Color(0xFF333333))
            .clickable { onClick() }
    ) {
        Column(

        ) {
            Text(text = note.title,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp))

            Text(text = note.content,
                color = Color(0xFF999999),
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
        }
    }
}