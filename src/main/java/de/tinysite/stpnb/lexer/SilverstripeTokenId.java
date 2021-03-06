package de.tinysite.stpnb.lexer;

import com.google.common.collect.ImmutableSet;
import de.tinysite.stpnb.antlr.StpParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

public enum SilverstripeTokenId implements TokenId {


    WS("WS", SilverstripeTokenIdCategory.WHITESPACE, StpParser.WS),
    TEXT("TEXT", SilverstripeTokenIdCategory.TEXT, StpParser.TEXT),
    COMMENT("COMMENT", SilverstripeTokenIdCategory.COMMENT, StpParser.COMMENT),
    OTAG("OTAG", SilverstripeTokenIdCategory.KEYWORD, StpParser.OTAG),
    UNKNOWN("UNKNOWN", SilverstripeTokenIdCategory.OTHER, StpParser.UNKNOWN),
    NUMBER("NUMBER", SilverstripeTokenIdCategory.NUMBER, StpParser.NUMBER),
    SUBPROPERTY("SUBPROPERTY", SilverstripeTokenIdCategory.FIELD, StpParser.SUBPROPERTY),
    PROPERTY("PROPERTY", SilverstripeTokenIdCategory.FIELD, StpParser.PROPERTY),
    IDENTIFIER("IDENTIFIER", SilverstripeTokenIdCategory.IDENTIFIER, StpParser.IDENTIFIER),
    OP_NOT("OP_NOT", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_NOT),
    OP_AND("OP_AND", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_AND),
    OP_OR("OP_OR", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_OR),
    OP_EQUAL("OP_EQUAL", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_EQUAL),
    OP_NOT_EQUAL("OP_NOT_EQUAL", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_NOT_EQUAL),
    OP_GREATER_THAN("OP_GREATER_THAN", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_GREATER_THAN),
    OP_LESS_THAN("OP_LESS_THAN", SilverstripeTokenIdCategory.OPERATOR, StpParser.OP_LESS_THAN),
    CTAG("CTAG", SilverstripeTokenIdCategory.KEYWORD, StpParser.CTAG),
    END_PREFIX("END_PREFIX", SilverstripeTokenIdCategory.KEYWORD, StpParser.END_PREFIX),
    INCLUDE("INCLUDE", SilverstripeTokenIdCategory.KEYWORD, StpParser.INCLUDE),
    WITH("WITH", SilverstripeTokenIdCategory.KEYWORD, StpParser.WITH),
    REQUIRE("REQUIRE", SilverstripeTokenIdCategory.KEYWORD, StpParser.REQUIRE),
    IF("IF", SilverstripeTokenIdCategory.KEYWORD, StpParser.IF),
    ELSE("ELSE", SilverstripeTokenIdCategory.KEYWORD, StpParser.ELSE),
    ELSE_IF("ELSE_IF", SilverstripeTokenIdCategory.KEYWORD, StpParser.ELSE_IF),
    LOOP("LOOP", SilverstripeTokenIdCategory.KEYWORD, StpParser.LOOP),
    CACHEBLOCK("CACHEBLOCK", SilverstripeTokenIdCategory.KEYWORD, StpParser.CACHEBLOCK),
    UNCACHED("UNCACHED", SilverstripeTokenIdCategory.KEYWORD, StpParser.UNCACHED),
    CONTROL("CONTROL", SilverstripeTokenIdCategory.KEYWORD, StpParser.CONTROL),
    BASE("BASE", SilverstripeTokenIdCategory.KEYWORD, StpParser.BASE),
    RESOURCE("RESOURCE", SilverstripeTokenIdCategory.KEYWORD, StpParser.RESOURCE),
    TRANSLATION("TRANSLATION", SilverstripeTokenIdCategory.KEYWORD, StpParser.TRANSLATION),
    OLD_TRANSLATION("OLD_TRANSLATION", SilverstripeTokenIdCategory.KEYWORD, StpParser.OLD_TRANSLATION),
    SPRINTF_TRANSLATION("SPRINTF_TRANSLATION", SilverstripeTokenIdCategory.KEYWORD, StpParser.SPRINTF_TRANSLATION),
    QUOTED_STRING("QUOTED_STRING", SilverstripeTokenIdCategory.STRING, StpParser.QUOTED_STRING),
    OPEN_BRACKET("OPEN_BRACKET", SilverstripeTokenIdCategory.BRACKET, StpParser.OPEN_BRACKET),
    CLOSING_BRACKET("CLOSING_BRACKET", SilverstripeTokenIdCategory.BRACKET, StpParser.CLOSING_BRACKET),
    COMMA("COMMA", SilverstripeTokenIdCategory.SEPARATOR, StpParser.COMMA),
    DOT("DOT", SilverstripeTokenIdCategory.SEPARATOR, StpParser.DOT),
    QUOTED_PARAM("QUOTED_PARAM", SilverstripeTokenIdCategory.STRING, StpParser.QUOTED_PARAM),
    ERROR_CHARACTER("ERROR_CHARACTER", SilverstripeTokenIdCategory.ERROR, StpParser.ERROR_CHARACTER);

    private static final Language<SilverstripeTokenId> language = new SilverstripeLanguageHierarchy().language();

    private final String name;

    private final SilverstripeTokenIdCategory primaryCategory;

    private final int id;

    private static final Map<Integer, SilverstripeTokenId> idToToken = new HashMap<>();

    private static Set<SilverstripeTokenId> matchable = ImmutableSet.of(
        COMMENT,
        OTAG,
        CTAG,
        CACHEBLOCK,
        CONTROL,
        ELSE,
        ELSE_IF,
        IF,
        LOOP,
        UNCACHED,
        WITH,
        END_PREFIX
    );

    SilverstripeTokenId(String name, SilverstripeTokenIdCategory primaryCategory, int id) {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.id = id;
    }
        
    public static Language getLanguage() {
        return language;
    }

    public static boolean hasMatches(SilverstripeTokenId token) {
        return matchable.contains(token);
    }

    static {
        for (SilverstripeTokenId token : SilverstripeTokenId.values()) {
            idToToken.put(token.id, token);
        }
    }

    @Override
    public String primaryCategory() {
        return primaryCategory.name().toLowerCase();
    }

    public static SilverstripeTokenId valueOf(int id) {
        return idToToken.get(id);
    }

    public int getId() {
        return id;
    }
}
