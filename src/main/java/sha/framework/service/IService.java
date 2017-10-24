package sha.framework.service;
import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.work.exception.TKRKScreenException;

public interface IService<S extends InputBaseData, T extends OutputBaseData> {

    public abstract void preMainLogic(S input, T output) throws TKRKScreenException;

    public abstract void execMainLogic(S input, T output, String... param) throws TKRKScreenException;

    public abstract void postMainLogic(S input, T output) throws TKRKScreenException;

}
