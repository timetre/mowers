package com.blablacar.mowers.common;

import com.blablacar.mowers.builder.LawnBuilder;
import com.blablacar.mowers.builder.LawnBuilderImpl;
import com.blablacar.mowers.builder.MowerBuilder;
import com.blablacar.mowers.builder.MowerBuilderImpl;
import com.blablacar.mowers.services.MowerService;
import com.blablacar.mowers.services.MowerServiceImpl;
import com.google.inject.AbstractModule;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MowerService.class).to(MowerServiceImpl.class).asEagerSingleton();
        bind(LawnBuilder.class).to(LawnBuilderImpl.class).asEagerSingleton();
        bind(MowerBuilder.class).to(MowerBuilderImpl.class).asEagerSingleton();
    }
}
