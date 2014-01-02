package org.bigbluebutton.endpoint

/**
 * This file contains messages received and sent from/to pubsub by bbb-apps.
 */
trait JsonMessagesFixtures {

/**
 *  Message received from pubsub to create a meeting. 
 *  
 *  meeting_descriptor: different default values for features of the meeting.
 */  
  val CreateMeetingRequestJson     = 
"""
	{
	    "header": {
	        "destination": {
	            "to": "apps_channel"
	        },
	        "reply": {
	            "to": "apps_channel",
	            "correlation_id": "abc"
	        },
	        "name": "create_meeting_request",
	        "timestamp": "2013-12-23T08:50Z",
	        "source": "web-api"
	    },
	    "payload": {
	        "meeting_descriptor": {
	            "name": "English 101",
	            "external_id": "english_101",
	            "record": true,
	            "welcome_message": "Welcome to English 101",
	            "logout_url": "http://www.bigbluebutton.org",
	            "avatar_url": "http://www.gravatar.com/bigbluebutton",
	            "max_users": 20,
	            "duration": {
	                "length_in_minutes": 120,
	                "allow_extend": false,
	                "max_minutes": 240
	            },
	            "voice_conference": {
	                "pin": 123456,
	                "number": 85115
	            },
	            "phone_numbers": [
	                {
	                    "number": "613-520-7600",
	                    "description": "Ottawa"
	                },
	                {
	                    "number": "1-888-555-7890",
	                    "description": "NA Toll-Free"
	                }
	            ],
	            "metadata": {
	                "customer_id": "acme-customer",
	                "customer_name": "ACME"
	            }
	        }
	    }
	}     
"""

/**
 * Message response to the create meeting request.
 * 
 * session: the session id for this newly created meeting.
 * result: the result of the request and relevant message.
 * meeting_descriptor: the meeting_descriptor passed on the create request.
 * 
 */
    
  val CreateMeetingResponseJson    = 
"""
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "create_meeting_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-1234",
        "result": {
            "success": true,
            "message": "Success"
        },
        "meeting_descriptor": {
            "name": "English 101",
            "external_id": "english_101",
            "record": true,
            "welcome_message": "Welcome to English 101",
            "logout_url": "http://www.bigbluebutton.org",
            "avatar_url": "http://www.gravatar.com/bigbluebutton",
            "max_users": 20,
            "duration": {
                "length_in_minutes": 120,
                "allow_extend": false,
                "max_minutes": 240
            },
            "voice_conference": {
                "pin": 123456,
                "number": 85115
            },
            "phone_numbers": [
                {
                    "number": "613-520-7600",
                    "description": "Ottawa"
                },
                {
                    "number": "1-888-555-7890",
                    "description": "NA Toll-Free"
                }
            ],
            "metadata": {
                "customer_id": "acme-customer",
                "customer_name": "ACME"
            }
        }
    }
}
"""

/**
 * Broadcast message to pubsub about the newly created meeting. 
 * Interested parties who keep track of running meeting listen for this
 * event and initialize their own data in preparation for users joining
 * the meeting. 
 *
 */    
  val MeetingCreatedEventJson      = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "meeting_created_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-1234",
        "meeting_descriptor": {
            "name": "English 101", 
            "external_id": "english_101",
            "record": true,
            "welcome_message": "Welcome to English 101",
            "logout_url": "http://www.bigbluebutton.org",
            "avatar_url": "http://www.gravatar.com/bigbluebutton",
            "max_users": 20,
            "duration": {
                "length_in_minutes": 120,
                "allow_extend": false,
                "max_minutes": 240
            },
            "voice_conference": {
                "pin": 123456,
                "number": 85115
            },
            "phone_numbers": [
                {
                    "number": "613-520-7600",
                    "description": "Ottawa"
                },
                {
                    "number": "1-888-555-7890",
                    "description": "NA Toll-Free"
                }
            ],
            "metadata": {
                "customer_id": "acme-customer",
                "customer_name": "ACME"
            }
        }
    }
}    
    """

/**
 *  Message received from the pubsub to end the meeting.
 *  
 *  force: true/false - kick everyone out and end the meeting
 *  warn_users: true/false - notify users and wait for a few seconds
 *                           before kicking everyone out. 
 */    
  val EndMeetingRequestJson        = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "end_meeting_request",
        "timestamp": "2013-12-23T08: 50Z",
        "source": "bbb-web"
    },
    "payload": {
        "meeting": {
            "name": "English101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "force": true,
        "warn_users": true
    }
}    
    """

/**
 * Response to the end meeting request.    
 */    
  val EndMeetingResponseJson       = """
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "end_meeting_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "result": {
            "success": true,
            "message": "Ending the meeting. Please wait several seconds to complete the request."
        }
    }
}    
    """

/**
 * Notify users that the meeting is about to end. This message gets sent when
 * an end meeting request is received and as a notice that a meeting is about
 * to reach its duration.
 * 
 * time_left, time_unit: time left before the users will be kicked out.
 * allow_extend: allow moderators to extend the meeting up to max duration.
 * 
 */    
  val EndMeetingWarningEventJson          = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "end_meeting_warning_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "time_left": 30,
        "time_unit": "seconds",
        "allow_extend": false
    }
}    
    """

/**
 * Broadcast message that the meeting has ended and that all users have been
 * kicked out. Interested parties should clean up their data.
 * 
 */    
  val MeetingEndedEventJson        = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "meeting_ended_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345"
    }
}    
    """

/**
 * Received from the pubsub that a user is about to join the meeting.     
 */    
  val RegisterUserRequestJson      = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "register_user_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-web"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user_descriptor": {
            "external_id": "user1",
            "name": "Guga",
            "role": "MODERATOR",
            "pin": 12345,
            "welcome_message": "Welcome to English 101",
            "logout_url": "http://www.example.com",
            "avatar_url": "http://www.example.com/avatar.png",
            "metadata": {
	           "student_id": "54321",
	           "program": "engineering"
	        }
        }
    }
}    
    """

/**
 * Response to the register user request.
 * 
 * user_token: auth token the user needs to pass when joining the meeting.
 * 
 */    
  val RegisterUserResponseJson     = """
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "register_user_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user_token": "guga-token",
        "result": {
            "success": true,
            "message": "Success"
        },
        "user_descriptor": {
            "external_id": "user1",
            "name": "Guga",
            "role": "MODERATOR",
            "pin": 12345,
            "welcome_message": "Welcome to English 101",
            "logout_url": "http://www.example.com",
            "avatar_url": "http://www.example.com/avatar.png",
            "metadata": {
	           "student_id": "54321",
	           "program": "engineering"
	        }
        }
    }
}    
    """

/**
 * Broadcast to pubsub that a user is about to join the meeting.
 */    
  val UserRegisteredEventJson      = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_registered_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-1234",
        "user_descriptor": {
            "external_id": "user1",
            "name": "Guga",
            "role": "MODERATOR",
            "pin": 12345,
            "welcome_message": "Welcome to English 101",
            "logout_url": "http://www.example.com",
            "avatar_url": "http://www.example.com/avatar.png",
            "metadata": {
	           "student_id": "54321",
	           "program": "engineering"
	        }
        }
    }
}    
    """

/**
 * Received when a user joins the meeting.
 * 
 * token: auth token returned on the register user response.
 */    
  val UserJoinRequestJson          = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "user_join_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-apps"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "token": "user1-token-1"
    }
}    
    """

/**
 * Response to the user join request when the token is valid.
 * 
 * The information about the user is returned with the user id.
 */    
  val UserJoinResponseJson         = """
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc-corelid"
        },
        "name": "user_join_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "id": "english_101",
            "name": "English 101"
        },
        "session": "english_101-1234",
        "result": {
            "success": true,
            "message": "Success"
        },
        "user": {
            "id": "juan-user1",
            "external_id": "juan-ext-user1",
            "name": "Juan Tamad",
            "role": "MODERATOR",
            "pin": 12345,
            "welcome_message": "Welcome Juan",
            "logout_url": "http://www.umaliska.don",
            "avatar_url": "http://www.mukhamo.com/unggoy",
            "metadata": {
	           "student_id": "54321",
	           "program": "engineering"
	        }
        }
    }
}    
    """

/**
 * Broadcast message to interested parties that a user has joined the meeting.
 * 
 */    
  val UserJoinedEventJson          = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_joined_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user": {
            "id": "juanid",
            "external_id": "userjuan",
            "name": "Juan Tamad",
            "role": "MODERATOR",
            "pin": 12345,
            "welcome_message": "Welcome Juan",
            "logout_url": "http://www.umaliska.don",
            "avatar_url": "http://www.mukhamo.com/unggoy",
            "is_presenter": true,
            "status": {
                "hand_raised": false,
                "muted": false,
                "locked": false,
                "talking": false
            },
            "caller_id": {
                "name": "Juan Tamad",
                "number": "011-63-917-555-1234"
            },
            "media_streams": [
                {
                    "media_type": "audio",
                    "uri": "http://cdn.bigbluebutton.org/stream/a1234"
                },
                {
                    "media_type": "video",
                    "uri": "http://cdn.bigbluebutton.org/stream/v1234"
                },
                {
                    "media_type": "screen",
                    "uri": "http://cdn.bigbluebutton.org/stream/s1234"
                }
            ],
            "metadata": {
	           "student_id": "54321",
	           "program": "engineering"
	        }
        }
    }
}    
    """

/**
 * Received message that a user has left the meeting.
 */    
  val UserLeaveEventJson           = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_leave_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user": {
            "id": "juanid",
            "name": "Juan Tamad"
        }
    }
}    
    """

/**
 * Broadcast message that a user has left the meeting.
 */    
  val UserLeftEventJson            = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_left_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user": {
            "id": "juanid",
            "name": "Juan Tamad"
        }
    }
}    
    """

/**
 * Received messages to get all the users in a meeting.
 */    
  val GetUsersRequestJson          = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "get_users_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "bbb-web"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "requester": {
            "id": "juanid",
            "name": "Juan Tamad"
        }
    }
}    
    """

/**
 * Response to the get users request.
 */    
  val GetUsersResponseJson         = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "get_users_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "users": [
            {
                "id": "juanid",
                "external_id": "userjuan",
                "name": "Juan Tamad",
                "role": "MODERATOR",
                "pin": 12345,
                "welcome_message": "Welcome Juan",
                "logout_url": "http://www.umaliska.don",
                "avatar_url": "http://www.mukhamo.com/unggoy",
                "is_presenter": true,
                "status": {
                    "hand_raised": false,
                    "muted": false,
                    "locked": false,
                    "talking": false
                },
                "caller_id": {
                    "name": "Juan Tamad",
                    "number": "011-63-917-555-1234"
                },
                "media_streams": [
                    {
                        "media_type": "audio",
                        "uri": "http://cdn.bigbluebutton.org/stream/a1234"
                    },
                    {
                        "media_type": "video",
                        "uri": "http://cdn.bigbluebutton.org/stream/v1234"
                    },
                    {
                        "media_type": "screen",
                        "uri": "http://cdn.bigbluebutton.org/stream/s1234"
                    }
                ],
                "metadata": {
	              "student_id": "54321",
	              "program": "engineering"
	            }
            }
        ]
    }
}    
    """

/**
 * Received message that a user has raised hand.
 */    
  val RaiseUserHandRequestJson     = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "raise_user_hand_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "requester": {
            "id": "juanid",
            "name": "Juan Tamad"
        },
        "raise": true
    }
}    
    """

/**
 * Broadcast message that a user has raise her/his hand.
 */    
  val UserRaisedHandEventJson      = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_raised_hand_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "requester": {
            "id": "juanid",
            "name": "Juan Tamad"
        },
        "raised": true
    }
}    
    """

/**
 * Received message to assign a user as a presenter.
 */    
  val AssignPresenterRequestJson   = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "assign_presenter_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "presenter": {
            "id": "user1",
            "name": "Guga"
        },
        "assigned_by": {
            "id": "user2",
            "name": "Juan"
        }
    }
}    
    """

/**
 * Broadcast message that a new presenter has been assigned.
 */    
  val PresenterAssignedEventJson   = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "presenter_assigned_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "presenter": {
            "id": "user1",
            "name": "Guga"
        },
        "assigned_by": {
            "id": "user2",
            "name": "Juan"
        }
    }
}    
    """

/**
 * Received message to mute a user.
 */    
  val MuteUserRequestJson          = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "mute_user_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user": {
            "id": "user1",
            "name": "Guga"
        },
        "requester": {
            "id": "user2",
            "name": "Juan"
        },
        "mute": true
    }
}    
    """

/**
 * Broadcast message that a request to mute a user has been received.
 */
  val MuteUserRequestEventJson     = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "mute_user_request_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "user": {
            "id": "user1",
            "name": "Guga"
        },
        "requester": {
            "id": "user2",
            "name": "Juan"
        },
        "mute": true
    }
}    
    """

/**
 * Broadcast message to the voice conference provider (FreeSWITCH) to mute
 * a user.
 */    
  val MuteVoiceUserRequestJson   = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "mute_voice_user_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "web-api"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "mute": true,
        "user_metadata": {
            "id": "user1",
            "name": "Guga"
        },
        "voice_metadata": {
            "FreeSWITCH-IPv4": "192.168.0.166",
            "Conference-Name": "72382",
            "Conference-Unique-ID": "480d3f7c-224f-11e0-ae04-fbe97e271da0",
            "conference_member_id": "1"
        }
    }
}    
    """

/**
 * Message from FreeSWITCH that the user has been muted.
 */    
    val VoiceUserMutedEventJson   = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "voice_user_muted_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "muted": true,
        "user_metadata": {
            "id": "user1",
            "name": "Guga"
        },
        "voice_metadata": {
            "FreeSWITCH-IPv4": "192.168.0.166",
            "Conference-Name": "72382",
            "Conference-Unique-ID": "480d3f7c-224f-11e0-ae04-fbe97e271da0",
            "conference_member_id": "1"
        }
    }
}   
    """

/**
 * Broadcast messages to interested parties that the user is now muted.
 */      
  val UserMutedEventJson           = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_muted_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "muted": true,
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}       
    """

val UserPublishStreamRequestJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "user_publish_stream_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """

val PublishStreamRequestJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "reply": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "publish_stream_request",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """  

val PublishStreamResponseJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "publish_stream_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video",
            "uri": "http://cdn.bigbluebutton.org/stream/v1234"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """   
  
val UserPublishStreamResponseJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel",
            "correlation_id": "abc"
        },
        "name": "user_publish_stream_response",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video",
            "uri": "http://cdn.bigbluebutton.org/stream/v1234"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """
  
val PublishedStreamEventJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "published_stream_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video",
            "uri": "http://cdn.bigbluebutton.org/stream/v1234"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """  
  
val UserPublishedStreamEventJson = """
{
    "header": {
        "destination": {
            "to": "apps_channel"
        },
        "name": "user_published_stream_event",
        "timestamp": "2013-12-23T08:50Z",
        "source": "fs-esl"
    },
    "payload": {
        "meeting": {
            "name": "English 101",
            "id": "english_101"
        },
        "session": "english_101-12345",
        "media": {
            "media_type": "video",
            "uri": "http://cdn.bigbluebutton.org/stream/v1234"
        },
        "user": {
            "id": "user1",
            "name": "Guga"
        }
    }
}
  """   
  
  
}