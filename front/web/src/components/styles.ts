import { blue, red } from "@mui/material/colors";

const styles = {
  api: {
    padding: "2px 40px",
  },
  // api == apiSection
  apiSection: {
    padding: "0px 40px",
    color: "black",
  },
  apiHeader: {
    width: "100%",
    justifyContent: "start",
    color: "black",
  },
  apiUri: {
    color: "blue",
    fontSize: 15,
    alignItems: "center",
    textTransform: "lowercase",
  },
  apiMethod: {
    post: {
      background: "blue",
      color: "white",
      padding: "4px 10px",
      fontSize: 13,
      borderRadius: 20,
    },
    patch: {
      background: "green",
      color: "white",
      padding: "4px 10px",
      fontSize: 13,
      borderRadius: 20,
    },
    delete: {
      background: "red",
      color: "white",
      padding: "4px 10px",
      fontSize: 13,
      borderRadius: 20,
    },
    get: {
      background: "purple",
      color: "white",
      padding: "4px 10px",
      fontSize: 13,
      borderRadius: 20,
    },
    least: {
      background: "black",
      color: "white",
      padding: "4px 10px",
      fontSize: 13,
      borderRadius: 20,
    },
    color: "white",
    padding: "4px 10px",
    fontSize: 13,
    borderRadius: 20,
  },
  apiDetail: {
    fontSize: 20,
    fontWeight: "bold",
    padding: 4,
    alignItems: "center",
  },
  apiTitle: {
    fontSize: 20,
    fontWeight: "bold",
    marginRight: 15,
  },
  apiType: {
    color: "grey",
  },
  apiRequired: {
    required: {
      color: "blue",
    },
    notRequired: {
      color: "red",
    },
  },
  outputHeader: {
    marginBottom: 12,
    width: "100%",
  },
  responseStatus: {
    success: {
      color: "blue",
    },
    failed: {
      color: "red",
    },
  },
  outputStyle: {
    border: "dotted blue 1px",
    borderRadius: 10,
    padding: 15,
    width: "100%",
    height: 217,
  },
};

export default styles;
