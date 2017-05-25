package com.lap.languagepicker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by HuyQuyet on 5/25/17.
 */

public class Language {
    public static final Language[] LANGUAGES = {
            new Language("aa", "Afar"),
            new Language("ab","Abkhazian"),
            new Language("ae","Avestan"),
            new Language("af","Afrikaans"),
            new Language("ak","Akan"),
            new Language("am","Amharic"),
            new Language("an","Aragonese"),
            new Language("ar","Arabic"),
            new Language("as","Assamese"),
            new Language("av","Avaric"),
            new Language("ay","Aymara"),
            new Language("az","Azerbaijani"),
            new Language("ba","Bashkir"),
            new Language("be","Belarusian"),
            new Language("bg","Bulgarian"),
            new Language("bh","Bihari languages"),
            new Language("bi","Bislama"),
            new Language("bm","Bambara"),
            new Language("bn","Bengali"),
            new Language("bo","Tibetan"),
            new Language("br","Breton"),
            new Language("bs","Bosnian"),
            new Language("ca","Catalan; Valencian"),
            new Language("ce","Chechen"),
            new Language("ch","Chamorro"),
            new Language("co","Corsican"),
            new Language("cr","Cree"),
            new Language("cs","Czech"),
            new Language("cu","Church Slavic; Old Slavonic; Church Slavonic; Old Bulgarian; Old Church Slavonic"),
            new Language("cv","Chuvash"),
            new Language("cy","Welsh"),
            new Language("da","Danish"),
            new Language("de","German"),
            new Language("dv","Divehi; Dhivehi; Maldivian"),
            new Language("dz","Dzongkha"),
            new Language("ee","Ewe"),
            new Language("el","Greek, Modern (1453-)"),
            new Language("en","English"),
            new Language("eo","Esperanto"),
            new Language("es","Spanish; Castilian"),
            new Language("et","Estonian"),
            new Language("eu","Basque"),
            new Language("fa","Persian"),
            new Language("ff","Fulah"),
            new Language("fi","Finnish"),
            new Language("fj","Fijian"),
            new Language("fo","Faroese"),
            new Language("fr","French"),
            new Language("fy","Western Frisian"),
            new Language("ga","Irish"),
            new Language("gd","Gaelic; Scottish Gaelic"),
            new Language("gl","Galician"),
            new Language("gn","Guarani"),
            new Language("gu","Gujarati"),
            new Language("gv","Manx"),
            new Language("ha","Hausa"),
            new Language("he","Hebrew"),
            new Language("hi","Hindi"),
            new Language("ho","Hiri Motu"),
            new Language("hr","Croatian"),
            new Language("ht","Haitian; Haitian Creole"),
            new Language("hu","Hungarian"),
            new Language("hy","Armenian"),
            new Language("hz","Herero"),
            new Language("ia","Interlingua (International Auxiliary Language Association)"),
            new Language("id","Indonesian"),
            new Language("ie","Interlingue; Occidental"),
            new Language("ig","Igbo"),
            new Language("ii","Sichuan Yi; Nuosu"),
            new Language("ik","Inupiaq"),
            new Language("io","Ido"),
            new Language("is","Icelandic"),
            new Language("it","Italian"),
            new Language("iu","Inuktitut"),
            new Language("ja","Japanese"),
            new Language("jv","Javanese"),
            new Language("ka","Georgian"),
            new Language("kg","Kongo"),
            new Language("ki","Kikuyu; Gikuyu"),
            new Language("kj","Kuanyama; Kwanyama"),
            new Language("kk","Kazakh"),
            new Language("kl","Kalaallisut; Greenlandic"),
            new Language("km","Central Khmer"),
            new Language("kn","Kannada"),
            new Language("ko","Korean"),
            new Language("kr","Kanuri"),
            new Language("ks","Kashmiri"),
            new Language("ku","Kurdish"),
            new Language("kv","Komi"),
            new Language("kw","Cornish"),
            new Language("ky","Kirghiz; Kyrgyz"),
            new Language("la","Latin"),
            new Language("lb","Luxembourgish; Letzeburgesch"),
            new Language("lg","Ganda"),
            new Language("li","Limburgan; Limburger; Limburgish"),
            new Language("ln","Lingala"),
            new Language("lo","Lao"),
            new Language("lt","Lithuanian"),
            new Language("lu","Luba-Katanga"),
            new Language("lv","Latvian"),
            new Language("mg","Malagasy"),
            new Language("mh","Marshallese"),
            new Language("mi","Maori"),
            new Language("mk","Macedonian"),
            new Language("ml","Malayalam"),
            new Language("mn","Mongolian"),
            new Language("mr","Marathi"),
            new Language("ms","Malay"),
            new Language("mt","Maltese"),
            new Language("my","Burmese"),
            new Language("na","Nauru"),
            new Language("nb","Bokmål, Norwegian; Norwegian Bokmål"),
            new Language("nd","Ndebele, North; North Ndebele"),
            new Language("ne","Nepali"),
            new Language("ng","Ndonga"),
            new Language("nl","Dutch; Flemish"),
            new Language("nn","Norwegian Nynorsk; Nynorsk, Norwegian"),
            new Language("no","Norwegian"),
            new Language("nr","Ndebele, South; South Ndebele"),
            new Language("nv","Navajo; Navaho"),
            new Language("ny","Chichewa; Chewa; Nyanja"),
            new Language("oc","Occitan (post 1500); Provençal"),
            new Language("oj","Ojibwa"),
            new Language("om","Oromo"),
            new Language("or","Oriya"),
            new Language("os","Ossetian; Ossetic"),
            new Language("pa","Panjabi; Punjabi"),
            new Language("pi","Pali"),
            new Language("pl","Polish"),
            new Language("ps","Pushto; Pashto"),
            new Language("pt","Portuguese"),
            new Language("qu","Quechua"),
            new Language("rm","Romansh"),
            new Language("rn","Rundi"),
            new Language("ro","Romanian; Moldavian; Moldovan"),
            new Language("ru","Russian"),
            new Language("rw","Kinyarwanda"),
            new Language("sa","Sanskrit"),
            new Language("sc","Sardinian"),
            new Language("sd","Sindhi"),
            new Language("se","Northern Sami"),
            new Language("sg","Sango"),
            new Language("si","Sinhala; Sinhalese"),
            new Language("sk","Slovak"),
            new Language("sl","Slovenian"),
            new Language("sm","Samoan"),
            new Language("sn","Shona"),
            new Language("so","Somali"),
            new Language("sq","Albanian"),
            new Language("sr","Serbian"),
            new Language("ss","Swati"),
            new Language("st","Sotho, Southern"),
            new Language("su","Sundanese"),
            new Language("sv","Swedish"),
            new Language("sw","Swahili"),
            new Language("ta","Tamil"),
            new Language("te","Telugu"),
            new Language("tg","Tajik"),
            new Language("th","Thai"),
            new Language("ti","Tigrinya"),
            new Language("tk","Turkmen"),
            new Language("tl","Tagalog"),
            new Language("tn","Tswana"),
            new Language("to","Tonga (Tonga Islands)"),
            new Language("tr","Turkish"),
            new Language("ts","Tsonga"),
            new Language("tt","Tatar"),
            new Language("tw","Twi"),
            new Language("ty","Tahitian"),
            new Language("ug","Uighur; Uyghur"),
            new Language("uk","Ukrainian"),
            new Language("ur","Urdu"),
            new Language("uz","Uzbek"),
            new Language("ve","Venda"),
            new Language("vi","Vietnamese"),
            new Language("vo","Volapük"),
            new Language("wa","Walloon"),
            new Language("wo","Wolof"),
            new Language("xh","Xhosa"),
            new Language("yi","Yiddish"),
            new Language("yo","Yoruba"),
            new Language("za","Zhuang; Chuang"),
            new Language("zh","Chinese"),
            new Language("zu","Zulu"),
        };


    public Language(){

    }
    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static Language getLanguageByCode(String languageCode) {
        languageCode = languageCode.toLowerCase();

        Language l = new Language();
        l.setCode(languageCode);

        int i = Arrays.binarySearch(LANGUAGES, l, new ISOCodeComparator());

        if (i < 0) {
            return null;
        } else {
            return LANGUAGES[i];
        }
    }

    public static Language getLanguageByName(String languageName) {

        Language l = new Language();
        l.setName(languageName);

        int i = Arrays.binarySearch(LANGUAGES, l, new ISONameComparator());

        if (i < 0) {
            return null;
        } else {
            return LANGUAGES[i];
        }
    }

    public static class ISOCodeComparator implements Comparator<Language> {
        @Override
        public int compare(Language l, Language l1) {
            return l.code.compareTo(l1.code);
        }
    }

    public static class ISONameComparator implements Comparator<Language> {
        @Override
        public int compare(Language l, Language l1) {
            return l.name.compareTo(l1.name);
        }
    }
}
