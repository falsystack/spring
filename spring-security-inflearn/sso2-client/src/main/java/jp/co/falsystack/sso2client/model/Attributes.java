package jp.co.falsystack.sso2client.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
@Builder
public class Attributes {

    private Map<String, Object> mainAttributes;
    private Map<String, Object> subAttributes;
    private Map<String, Object> otherAttributes;
}
