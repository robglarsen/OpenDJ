/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions Copyright [year] [name of copyright owner]".
 *
 * Copyright 2006-2008 Sun Microsystems, Inc.
 * Portions Copyright 2014-2015 ForgeRock AS.
 */
package org.opends.server.types.operation;
import org.forgerock.i18n.LocalizableMessage;



import org.opends.server.types.AuthenticationType;
import org.forgerock.opendj.ldap.ByteString;


/**
 * This class defines a set of methods that are available for use by
 * pre-parse plugins for bind operations.  Note that this
 * interface is intended only to define an API for use by plugins and
 * is not intended to be implemented by any custom classes.
 */
@org.opends.server.types.PublicAPI(
     stability=org.opends.server.types.StabilityLevel.UNCOMMITTED,
     mayInstantiate=false,
     mayExtend=false,
     mayInvoke=true)
public interface PreParseBindOperation
       extends PreParseOperation
{
  /**
   * Retrieves the authentication type for this bind operation.
   *
   * @return  The authentication type for this bind operation.
   */
  AuthenticationType getAuthenticationType();



  /**
   * Retrieves a string representation of the protocol version
   * associated with this bind request.
   *
   * @return  A string representation of the protocol version
   *          associated with this bind request.
   */
  String getProtocolVersion();



  /**
   * Specifies the string representation of the protocol version
   * associated with this bind request.
   *
   * @param  protocolVersion  The string representation of the
   *                          protocol version associated with this
   *                          bind request.
   */
  void setProtocolVersion(String protocolVersion);



  /**
   * Retrieves the raw, unprocessed bind DN for this bind operation as
   * contained in the client request.  The value may not actually
   * contain a valid DN, as no validation will have been performed.
   *
   * @return  The raw, unprocessed bind DN for this bind operation as
   *          contained in the client request.
   */
  ByteString getRawBindDN();



  /**
   * Specifies the raw, unprocessed bind DN for this bind operation.
   *
   * @param  rawBindDN  The raw, unprocessed bind DN for this bind
   */
  void setRawBindDN(ByteString rawBindDN);



  /**
   * Retrieves the simple authentication password for this bind
   * operation.
   *
   * @return  The simple authentication password for this bind
   *          operation.
   */
  ByteString getSimplePassword();



  /**
   * Specifies the simple authentication password for this bind
   * operation.
   *
   * @param  simplePassword  The simple authentication password for
   *                         this bind operation.
   */
  void setSimplePassword(ByteString simplePassword);



  /**
   * Retrieves the SASL mechanism for this bind operation.
   *
   * @return  The SASL mechanism for this bind operation, or
   *          <CODE>null</CODE> if the bind does not use SASL
   *          authentication.
   */
  String getSASLMechanism();



  /**
   * Retrieves the SASL credentials for this bind operation.
   *
   * @return  The SASL credentials for this bind operation, or
   *          <CODE>null</CODE> if there are none or if the bind does
   *          not use SASL authentication.
   */
  ByteString getSASLCredentials();



  /**
   * Specifies the SASL credentials for this bind operation.
   *
   * @param  saslMechanism    The SASL mechanism for this bind
   *                          operation.
   * @param  saslCredentials  The SASL credentials for this bind
   *                          operation, or <CODE>null</CODE> if there
   *                          are none.
   */
  void setSASLCredentials(String saslMechanism, ByteString saslCredentials);



  /**
   * Specifies the set of server SASL credentials to include in the
   * bind response.
   *
   * @param  serverSASLCredentials  The set of server SASL credentials
   *                                to include in the bind response.
   */
  void setServerSASLCredentials(ByteString serverSASLCredentials);



  /**
   * Specifies the reason that the authentication failed.
   *
   * @param  reason  A human-readable message providing the reason
   *                 that the authentication failed.
   */
  void setAuthFailureReason(LocalizableMessage reason);
}

