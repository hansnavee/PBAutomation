"""Print an authorized-user JSON for the GOOGLE_CHAT_CREDENTIALS secret.

Run once locally. Do not commit the printed JSON or the OAuth client file.

    pip install google-auth-oauthlib
    python scripts/authorize_google_chat.py path\\to\\client_secret.json
"""

import sys

SCOPE = "https://www.googleapis.com/auth/chat.messages.create"


def main():
    if len(sys.argv) != 2:
        print("Usage: python scripts/authorize_google_chat.py path\\to\\client_secret.json", file=sys.stderr)
        return 1
    try:
        from google_auth_oauthlib.flow import InstalledAppFlow
    except ImportError:
        print("Install the login helper first: pip install google-auth-oauthlib", file=sys.stderr)
        return 1

    flow = InstalledAppFlow.from_client_secrets_file(sys.argv[1], [SCOPE])
    credentials = flow.run_local_server(port=0)
    print(credentials.to_json())
    return 0


if __name__ == "__main__":
    sys.exit(main())
