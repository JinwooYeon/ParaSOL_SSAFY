#include <iostream>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <cstring>
using namespace std;

#define BUF_SIZE 256

struct payload_a {
	char msg[10];
	char len[2];
};

struct payload_b {
	char account_number[14];
};

int main(int argc, char **argv) {
	int sock;
	struct sockaddr_in serv_addr;
	char message[BUF_SIZE];
	int str_len;

	if (argc != 3) {
		cout << "Usage: " << argv[0] << " <IP> <PORT>" << "\n";
		exit(1);
	}

	char *host = argv[1];
	int port = atoi(argv[2]);

	sock = socket(PF_INET, SOCK_STREAM, 0);
	if (sock == -1) {
		cout << "socket() error" << "\n";
		exit(1);
	}

	memset(&serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr(argv[1]);
	serv_addr.sin_port = htons(port);

	if (connect(sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1) {
		cout << "connect() error" << "\n";
		exit(1);
	}

	int recv_cnt, recv_len;
	while(1) {
		fputs("Input message(Q to quit): ", stdout);
		fgets(message, BUF_SIZE, stdin);
		cout << "My message: " << message << "\n";
		message[strlen(message) - 1] = 0;
	
		if (!strcmp(message, "Q\n")) break;
		
		struct payload_a test_payload_a = { "test", "4" };
		//str_len=write(sock, message, strlen(message));
		str_len=write(sock, &test_payload_a, sizeof(test_payload_a));
		memset(message, 0, sizeof(message));

		int status = 0;
		recv_len = read(sock, &status, sizeof(status));

		message[str_len] = 0;
		
		cout << "Message from server: " << status << "\n";
		
		struct payload_b test_payload_b = { "352-115-72535" };
		test_payload_b.account_number[13] = '6';
		//str_len=write(sock, message, strlen(message));
		str_len=write(sock, &test_payload_b, sizeof(test_payload_b));

		status = 0;
		recv_len = read(sock, &status, sizeof(status));

		cout << "Message from server: " << status << "\n";
	}

	close(sock);
	return 0;
}


