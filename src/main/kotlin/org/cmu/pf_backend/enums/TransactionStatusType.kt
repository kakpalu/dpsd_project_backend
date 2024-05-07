package org.cmu.pf_backend.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class TransactionStatusType(@get:JsonValue val value: String) {
    pending("pending"),
    succeeded("succeeded"),
    failed("failed"),
    exception("exception");

    companion object {
        @JvmStatic @JsonCreator
        fun fromInt(intValue: String?): TransactionStatusType? {
            if (intValue == null) return null
            for (i in entries) {
                if (i.value == intValue) return i
            }
            return null
        }
    }
}