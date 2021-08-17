package com.mabcci.domain.proposal.util;

import com.mabcci.domain.proposal.domain.ProposalFilter;
import org.springframework.core.convert.converter.Converter;

public class StringToProposalConverter implements Converter<String, ProposalFilter> {
    @Override
    public ProposalFilter convert(String value) {
        return ProposalFilter.valueOf(value.toUpperCase());
    }
}
