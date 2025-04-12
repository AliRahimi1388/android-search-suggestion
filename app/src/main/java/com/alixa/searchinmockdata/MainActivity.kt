package com.alixa.searchinmockdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alixa.searchinmockdata.ui.theme.SearchInMockDataTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchInMockDataTheme {
                val viewmodel = viewModel<MainViewModel>()
                val searchText by viewmodel.searchText.collectAsState()
                val persons by viewmodel.persons.collectAsState()
                val isSearching by viewmodel.isSearching.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        TextField(
                            value = searchText,
                            onValueChange = viewmodel::onSearchTextChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            placeholder = { Text(text = "Search") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentPadding = innerPadding

                        ) {
                            items(persons.size) { index ->
                                val person = persons[index]
                                Text(
                                    text = "${person.firstName} ${person.lastName}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}