package com.ericduncandev.TennisTrackr.TennisGame.Records.User;

import com.ericduncandev.TennisTrackr.TennisGame.Records.Geo;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
