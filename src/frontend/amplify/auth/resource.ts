import { defineAuth } from '@aws-amplify/backend';

/**
 * Define and configure your auth resource
 * @see https://docs.amplify.aws/gen2/build-a-backend/auth
 */
export const auth = defineAuth({
  loginWith: {
    email: true,
  },
  userAttributes: {
    "custom:employee_id": {
      dataType: "Number",
      mutable: false,
      min: 1,
      max: 1000000,
    },
    "custom:dni": {
      dataType: "String",
      mutable: false,
      maxLen: 8,
      minLen: 8,
    },
    "custom:area": {
      dataType: "String",
      mutable: true,
      maxLen: 120,
      minLen: 1,
    },
    "custom:position": {
      dataType: "String",
      mutable: true,
      maxLen: 120,
      minLen: 1,
    },
    "custom:is_representative": {
      dataType: "Boolean",
      mutable: true,
    },
  },
});
