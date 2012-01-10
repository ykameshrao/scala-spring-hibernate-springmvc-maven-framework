package com.kaleidosoftlabs.util

import javax.servlet._


/**
 * Utility class for UTF-8 Servlet Filter. This filter would be used to
 * consistently set the character encoding as UTF-8.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:45 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class Utf8Filter extends Filter {
  override def destroy = {}


  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    request.setCharacterEncoding("UTF-8")
    chain.doFilter(request, response)
  }


  override def init(filterConfig: FilterConfig)= {}
}