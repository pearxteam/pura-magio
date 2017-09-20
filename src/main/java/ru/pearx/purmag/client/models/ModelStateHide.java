package ru.pearx.purmag.client.models;

import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.Models;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/*
 * Created by mrAppleXZ on 20.09.17 22:27.
 */
public class ModelStateHide implements IModelState
{
    private List<String> groupsToHide;

    public ModelStateHide(String... groups)
    {
        groupsToHide = Arrays.asList(groups);
    }

    @Override
    public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> part)
    {
        if(part.isPresent())
        {
            Iterator<String> it = Models.getParts(part.get());
            while (it.hasNext())
            {
                String s = it.next();
                for(String cond : groupsToHide)
                {
                    if(s.startsWith(cond + "_"))
                        return Optional.empty();
                }
                return Optional.of(TRSRTransformation.identity());
            }
        }
        //keep the part
        return Optional.empty();
    }
}
