package com.heliang.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 *     
 * 项目名称：cloud-gateway-filter    
 * 类名称：TokenFilter    
 * 类描述：    
 * 创建人：个人姓名   
 * 创建时间：2019年3月10日 下午3:54:54    
 * 修改人：jolin    
 * 修改时间：2019年3月10日 下午3:54:54    
 * 修改备注：    
 *
 */
public class TokenFilter implements GlobalFilter, Ordered {

    Logger logger=LoggerFactory.getLogger( TokenFilter.class );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            logger.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

