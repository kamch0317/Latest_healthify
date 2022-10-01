package com.example.healthify

import com.google.firebase.firestore.DocumentId

data class Comment (@DocumentId
                    var id : String? = null,
                    var username : String? = null,
                    var commentTitle  : String? = null,
                    var commentDesc : String? = null)
