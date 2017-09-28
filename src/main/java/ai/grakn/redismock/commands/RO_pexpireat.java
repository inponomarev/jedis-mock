package ai.grakn.redismock.commands;

import ai.grakn.redismock.RedisBase;
import ai.grakn.redismock.Response;
import ai.grakn.redismock.Slice;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static ai.grakn.redismock.Utils.convertToLong;

class RO_pexpireat extends AbstractRedisOperation {
    RO_pexpireat(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    public Slice execute() {
        long deadline = convertToLong(new String(params().get(1).data(), StandardCharsets.UTF_8));
        return Response.integer(base().setDeadline(params().get(0), deadline));
    }
}
