package com.xiekang.exercise.pipe.manager;

import com.xiekang.exercise.pipe.Pipe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpPipeManager {

    private List<Pipe> pipeList = new CopyOnWriteArrayList();

    public List<Pipe> getPipes(){
        return pipeList;
    }

    public void register(Pipe pipe){
        pipeList.add(pipe);
    }

}
