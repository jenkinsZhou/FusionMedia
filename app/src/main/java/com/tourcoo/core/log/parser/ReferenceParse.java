package com.tourcoo.core.log.parser;



import androidx.annotation.NonNull;

import com.tourcoo.core.log.Parser;
import com.tourcoo.core.log.utils.ObjectUtil;

import java.lang.ref.Reference;

/**
 * Created by pengwei on 16/3/22.
 */
class ReferenceParse implements Parser<Reference> {
    @NonNull
    @Override
    public Class<Reference> parseClassType() {
        return Reference.class;
    }

    @Override
    public String parseString(@NonNull Reference reference) {
        Object actual = reference.get();
        if (actual == null) {
            return "get reference = null";
        }
        String result = reference.getClass().getSimpleName() + "<"
                + actual.getClass().getSimpleName() + "> {" + "→" + ObjectUtil.objectToString(actual);
        return result + "}";
    }
}
