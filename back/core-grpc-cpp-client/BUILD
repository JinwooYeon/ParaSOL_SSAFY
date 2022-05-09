package(default_visibility = ["//visibility:public"])

load("@rules_proto//proto:defs.bzl", "proto_library")
load("@rules_cc//cc:defs.bzl", "cc_binary", "cc_proto_library")
load("@com_github_grpc_grpc//bazel:cc_grpc_library.bzl", "cc_grpc_library")

proto_library(
    name = "core_api_proto",
    srcs = ["core_api.proto"],
)

cc_proto_library(
    name = "core_api_cc_proto",
    deps = [":core_api_proto"],
)

cc_grpc_library(
    name = "core_api_cc_grpc",
    srcs = [":core_api_proto"],
    grpc_only = True,
    deps = [":core_api_cc_proto"],
)

cc_binary(
    name = "core_api_server_cc",
    srcs = ["server.cpp"],
    deps = [
        "@com_github_grpc_grpc//:grpc++",
        ":core_api_cc_grpc"
    ]
)

cc_binary(
    name = "core_api_client_cc",
    srcs = ["client.cpp"],
    deps = [
        "@com_github_grpc_grpc//:grpc++",
        ":core_api_cc_grpc"
    ]
)