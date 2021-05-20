/* eslint @typescript-eslint/no-unused-vars: off */
/**
 * ua.trip.maps.api.v1.MapsApi
 * Maps Service
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: ermakovsa03@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import {
    LocationDTO,
    RouteInputDTO,
    RouteOutputDTO,
    SummaryDTO,
    TravelModeDTO,
} from "../model";

import {
    ApplicationApis,
    DefaultApiInterface,
} from "../api";

function reject(operation: string) {
    return () => Promise.reject(new Error("Unexpected function call " + operation));
}

export function mockApplicationApis({
    defaultApi = mockDefaultApi(),
}: Partial<ApplicationApis>): ApplicationApis {
    return { defaultApi };
}

export function mockDefaultApi(operations: {
    getRoute?: () => Promise<RouteOutputDTO>;
} = {}): DefaultApiInterface {
    return {
        getRoute: operations.getRoute || reject("DefaultApi.getRoute"),
    };
}